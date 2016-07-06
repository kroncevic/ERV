package hr.tvz.rome;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.rome.controllers.entities.EvidenceRequest;
import hr.tvz.rome.model.Employee;
import hr.tvz.rome.model.decorators.EvidenceDecorator;
import hr.tvz.rome.repository.EmployeesRepository;
import hr.tvz.rome.security.entity.JwtAuthenticationRequest;
import hr.tvz.rome.security.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RomeApplication.class)
@WebAppConfiguration
public class RestTests {

    private static final String CLAIM_USERNAME = "username";
    private static final String CLAIM_AUTHORITY = "authority";

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @BeforeClass
    public static void setupJndi() throws Exception {
        JndiInit.initializeJndi();
    }

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    private EmployeesRepository employeesRepository;

    private String token;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).apply(SecurityMockMvcConfigurers.springSecurity()).build();
        JwtAuthenticationRequest request = new JwtAuthenticationRequest();
        request.setUsername("itomic");
        request.setPassword("password");

        MvcResult loginResult = mockMvc.perform(post("/rest/auth")
                .content(json(request))
                .contentType(contentType)).andExpect(status().isOk()).andReturn();


        Pattern p = Pattern.compile("\\{\"token\":\"(.*)\"\\}");
        Matcher m = p.matcher(loginResult.getResponse().getContentAsString());
        if (m.find()) {
            token = m.group(1);
        }
    }

    @Test
    public void testUserInfo() throws Exception {
        MvcResult result = mockMvc.perform(get("/rest/user")
                .header("Authorization", token))
                .andExpect(status().isOk()).andReturn();

        ObjectMapper mapper = new ObjectMapper();

        Object authentication = mapper.readValue(result.getResponse().getContentAsByteArray(), Object.class);


        Assert.assertNotNull(authentication);

    }

    @Test
    public void testLogin() throws Exception {


        JwtAuthenticationRequest request = new JwtAuthenticationRequest();
        request.setUsername("itomic");
        request.setPassword("password");

        MvcResult loginResult = mockMvc.perform(post("/rest/auth")
                .content(json(request))
                .contentType(contentType)).andExpect(status().isOk()).andReturn();


        Pattern p = Pattern.compile("\\{\"token\":\"(.*)\"\\}");
        Matcher m = p.matcher(loginResult.getResponse().getContentAsString());
        String token = null;
        if (m.find()) {
            token = m.group(1);
        }

        Claims claims = jwtTokenUtil.getClaimsFromToken(token);

        String username = (String) claims.get(CLAIM_USERNAME);
        String authority = (String) claims.get(CLAIM_AUTHORITY);

        Assert.assertEquals(username, request.getUsername());

        Employee employee = employeesRepository.findByUsername(username);

        Assert.assertNotNull(employee);

        Assert.assertEquals(authority, employee.getAuthorization());


    }

    @Test
    public void testEvidenceController() throws Exception {

        mockMvc.perform(get("/rest/evidence/"))
                .andExpect(status().isUnauthorized());

        MvcResult resultAll = mockMvc.perform(get("/rest/evidence/")
                .header("Authorization", token))
                .andExpect(status().isOk()).andReturn();

        ObjectMapper mapper = new ObjectMapper();

        List<EvidenceDecorator> evidenceDecorators = mapper.readValue(resultAll.getResponse().getContentAsByteArray(), List.class);

        Assert.assertNotNull(evidenceDecorators);

        MvcResult resultSingleUser = mockMvc.perform(get("/rest/evidence/itomic")
                .header("Authorization", token))
                .andExpect(status().isOk()).andReturn();

        List<EvidenceDecorator> evidenceDecoratorsSingleUser = mapper.readValue(resultSingleUser.getResponse().getContentAsByteArray(), List.class);

        Assert.assertNotNull(evidenceDecoratorsSingleUser);

        MvcResult resultToday = mockMvc.perform(get("/rest/evidence/today")
                .header("Authorization", token))
                .andExpect(status().isOk()).andReturn();

        List<EvidenceDecorator> evidenceToday = mapper.readValue(resultToday.getResponse().getContentAsByteArray(), List.class);

        Assert.assertNotNull(evidenceToday);

        MvcResult resultLatest = mockMvc.perform(get("/rest/evidence/latest/itomic")
                .header("Authorization", token))
                .andExpect(status().isOk()).andReturn();

        EvidenceDecorator latestEvidence = mapper.readValue(resultLatest.getResponse().getContentAsByteArray(), EvidenceDecorator.class);

        Assert.assertNotNull(latestEvidence);

        EvidenceRequest evidenceRequest = new EvidenceRequest();
        evidenceRequest.setUsername("itomic");
        evidenceRequest.setType("Prijava");
        evidenceRequest.setProject("PrviProjekt");
        evidenceRequest.setLocation("Zagreb");
        evidenceRequest.setUniqueId(UUID.randomUUID().toString());

        mockMvc.perform(post("/rest/evidence")
                .content(json(evidenceRequest))
                .contentType(contentType)
                .header("Authorization", token))
        .andExpect(status().isOk()).andReturn();

        MvcResult resultLatestTest = mockMvc.perform(get("/rest/evidence/latest/itomic")
                .header("Authorization", token))
                .andExpect(status().isOk()).andReturn();

        EvidenceDecorator latestEvidenceTest = mapper.readValue(resultLatestTest.getResponse().getContentAsByteArray(), EvidenceDecorator.class);

        Assert.assertNotNull(latestEvidenceTest);

        mockMvc.perform(delete("/rest/evidence/"+latestEvidenceTest.getUniqueId())
                .header("Authorization", token))
                .andExpect(status().isOk()).andReturn();




    }


    private String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
