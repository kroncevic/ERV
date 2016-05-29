package hr.tvz.rome.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marko on 15.5.2016..
 */
@Component
public class JdbcAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    DataSource dataSource;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Connection connection = null;

        String authorization = null;

        try {
            connection = dataSource.getConnection();

            PreparedStatement authenticateAgainstDatasource = connection
                    .prepareStatement("SELECT authority FROM user_auth WHERE userName = ? AND password = ?");

            authenticateAgainstDatasource.setString(1, (String) authentication.getPrincipal());

            Md5PasswordEncoder encoder = new Md5PasswordEncoder();

            authenticateAgainstDatasource.setString(2, encoder.encodePassword((String) authentication.getCredentials(), null));

            ResultSet resultSet = authenticateAgainstDatasource.executeQuery();

            while (resultSet != null && resultSet.next()) {
                authorization = resultSet.getString("authority");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (authorization != null) {
            GrantedAuthority authority = new SimpleGrantedAuthority(authorization);
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(authority);
            return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), null, authorities);
        } else {
            throw new UsernameNotFoundException("No user found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return Authentication.class.isAssignableFrom(authentication);
    }

}
