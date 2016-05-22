package hr.tvz.rome.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Marko on 15.5.2016..
 */
@RestController
public class SessionController {


    @RequestMapping("rest/user")
    public Authentication user(Principal user) {
        return (Authentication) user;
    }

    @RequestMapping(value = "/rest/logout", method = RequestMethod.POST)
    public ResponseEntity<Void> logout() {
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
