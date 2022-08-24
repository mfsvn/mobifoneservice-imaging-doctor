package com.tunv.ham10000.controller;

import com.tunv.ham10000.Ham10000Application;
import com.tunv.ham10000.entity.Users;
import com.tunv.ham10000.repo.UsersRepo;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
public class RegisterController {

    @Autowired
    UsersRepo usersRepo;

    @GetMapping("/register")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String us = (String) session.getAttribute("user");
        if (us != null && us.length() > 0) {
            return "redirect:/home";
        }
         return "register";
    }

    @PostMapping("/postregister")
    public String login(@RequestParam(value="user") String user,
                        @RequestParam(value="pass") String pass,
                        HttpServletRequest request,
                        HttpServletResponse response) throws NoSuchAlgorithmException {

        Users users = usersRepo.findAllByUsername(user);
        if (users != null) {
            return "redirect:/home";
        } else {
            Users u = new Users();
            u.setUsername(user);

            String md5pass = Ham10000Application.getMD5(pass);
            u.setPassword(md5pass);
            System.out.println("md5pass = " + MD5Encoder.encode(pass.getBytes()));
            u.setId(null);
            usersRepo.save(u);

            return "redirect:/login";
        }

//        if (user.equals("tunv") && pass.equals("tunguyenaz1")) {
//            HttpSession session = request.getSession();
//            session.setAttribute("tunv", user);
//
//            // Remember me
//            Cookie c = new Cookie("tunv", "tunguyen");
//            c.setPath("/");
//            c.setMaxAge(30*24*60*60);
//            response.addCookie(c);
//            return "redirect:/menu";
//        }
//        else {
//            return "redirect:/login";
//        }
    }
//
//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        session.removeAttribute("user");
//        return "redirect:/login";
//    }
}
