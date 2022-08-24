package com.tunv.ham10000.controller;

import com.tunv.ham10000.Ham10000Application;
import com.tunv.ham10000.entity.Users;
import com.tunv.ham10000.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
public class LoginController {

    @Autowired
    UsersRepo usersRepo;

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String us = (String) session.getAttribute("user");
        if (us != null && us.length() > 0) {
            return "redirect:/home";
        }
         return "login";
    }

    @PostMapping("/postlogin")
    public String login(@RequestParam(value="user") String user, @RequestParam(value="pass") String pass,
                        HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {

        Users us = usersRepo.findAllByUsernameAndPassword(user, Ham10000Application.getMD5(pass));

        if (us!= null &&  user.equals(us.getUsername()) && Ham10000Application.getMD5(pass).equals(us.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Remember me
            Cookie c = new Cookie("user", us.getUsername());
            c.setPath("/");
            c.setMaxAge(30*24*60*60);
            response.addCookie(c);
            return "redirect:/home";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "redirect:/login";
    }
}
