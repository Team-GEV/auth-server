package com.bfs.authserver.controller;

import com.bfs.authserver.dao.UserDAO;
import com.bfs.authserver.pojo.User;
import com.bfs.authserver.security.CookieUtil;
import com.bfs.authserver.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;


@Controller
public class RegisterController {

    @Autowired
    UserDAO userDAO;

    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKeyemployee";


    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(HttpServletResponse httpServletResponse, String username, String password, String firstname, String lastname, String email, String redirect, Model model){

        if (username == null || password == null || firstname == null || lastname == null || email == null){
            model.addAttribute("error", "Invalid information!");
            return "register";
        }

        //default rolename = employee
        User user = new User(null, username, password, firstname, lastname, email, "employee");

        userDAO.createUser(user);

        String token = JwtUtil.generateToken(signingKey, username, user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getRolename());
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");

        return "redirect:" + redirect;
    }

}
