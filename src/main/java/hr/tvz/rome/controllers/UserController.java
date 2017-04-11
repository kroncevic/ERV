package hr.tvz.rome.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @RequestMapping("rest/user")
    public Authentication user(Principal user) {
        return (Authentication) user;
    }

}
