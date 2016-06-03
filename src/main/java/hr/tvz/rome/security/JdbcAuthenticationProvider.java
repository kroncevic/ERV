package hr.tvz.rome.security;

import hr.tvz.rome.model.Employee;
import hr.tvz.rome.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marko on 15.5.2016..
 */
@Component
public class JdbcAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private EmployeesRepository employeesRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        String authorization;

        Employee employee = employeesRepository.findByUsername((String) authentication.getPrincipal());

        if (employee != null && passwordEncoder.matches((String) authentication.getCredentials(), employee.getPassword())) {
            authorization = employee.getAuthorization();
            GrantedAuthority authority = new SimpleGrantedAuthority(authorization);
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(authority);
            return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), null, authorities);
        } else {
            throw new UsernameNotFoundException("No user with that credentials found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return Authentication.class.isAssignableFrom(authentication);
    }

}
