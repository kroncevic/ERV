package hr.tvz.rome.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Marko on 15.5.2016..
 */
@RestController
public class UserController {

    @RequestMapping("rest/user")
    public Authentication user(Principal user) {
        return (Authentication) user;
    }

}
