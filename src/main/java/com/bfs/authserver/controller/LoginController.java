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
public class LoginController {

    @Autowired
    UserDAO userDAO;

    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKeyemployee";


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletResponse httpServletResponse, String username, String email, String password, String redirect, Model model){

        User user = userDAO.validateUser(username, password);
        if (username == null || user == null){
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }

        String token = JwtUtil.generateToken(signingKey, username, user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getRolename());
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");

        return "redirect:" + redirect;
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse httpServletResponse, String username, String redirect, Model model){
        CookieUtil.clear(httpServletResponse, username);
        return "redirect:" + redirect;
    }
}
