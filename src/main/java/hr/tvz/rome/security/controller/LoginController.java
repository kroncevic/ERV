package hr.tvz.rome.security.controller;

import hr.tvz.rome.security.JdbcAuthenticationProvider;
import hr.tvz.rome.security.entity.JwtAuthenticationRequest;
import hr.tvz.rome.security.entity.JwtAuthenticationResponse;
import hr.tvz.rome.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private JdbcAuthenticationProvider authenticationProvider;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "rest/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwtTokenUtil.generateToken(authentication)));
    }


}
