package com.tunv.ham10000.controller;

import com.tunv.ham10000.Ham10000Application;
import com.tunv.ham10000.entity.Users;
import com.tunv.ham10000.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
public class PredictController {

    @Value("${file_upload_location}")
    String file_upload_location;

    @Value("${cdn_location}")
    String cdn_location;

    @Autowired
    UsersRepo usersRepo;

    @GetMapping("/predict")
    public String predict() {

        return null;
    }


    @PostMapping("/predict")
    public String predict(
                        @RequestParam("image") MultipartFile image,
                        HttpServletRequest request,
                        HttpServletResponse response, Model model) throws IOException {

        HttpSession session = request.getSession();
        String us = (String) session.getAttribute("user");
        if (us != null && us.length() > 0) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_S");
            if (image.getOriginalFilename() != null && image.getOriginalFilename().trim().length() > 0) {
                System.out.println(image.getOriginalFilename());
                String name = us  + "-" + df.format(new Date()) + "-" + new Random().nextInt(5000) + "." + image.getOriginalFilename().split("\\.")[1];
//                knowledge.setImage(name);
                saveMultipartFile("image", image,   name);



                return "redirect:/home?user=" + us + "&&image=" + name;
            }
        }
        return "login";
    }

    public void saveMultipartFile(String type, MultipartFile file, String name ) throws IOException {

        String uploadRootPath = file_upload_location ;


        if (type.equals("image")) {

            File uploadRootDir = new File(uploadRootPath);

            File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(file.getBytes());
            stream.close();
        }
    }
}
