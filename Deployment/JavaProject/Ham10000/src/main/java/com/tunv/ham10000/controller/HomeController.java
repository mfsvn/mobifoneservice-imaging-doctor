package com.tunv.ham10000.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;

@Controller
public class HomeController {

    @Value("${cdn_location}")
    String cdn_location;

    @Value("${file_upload_location}")
    String file_upload_location;

    @GetMapping("/home")
    public String root(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        String us = (String) session.getAttribute("user");
        if (us != null && us.length() > 0) {
            if (request.getParameter("user") != null) {
                String user = (String) request.getParameter("user");
                String image = (String) request.getParameter("image");

                System.out.println(user);
                System.out.println(image);

                final String uri = "http://35.220.190.201:8000/predict/";

                RestTemplate restTemplate = new RestTemplate();

                String input = "US";
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                String path = file_upload_location + image;
                System.out.println(path);
                headers.add("image_path", path);

                MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
                map.add("image_path",path);

                HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

                String response = restTemplate.postForObject(uri, entity,  String.class);

                System.out.println(response);

                JsonObject jo = new Gson().fromJson(response, JsonObject.class);
                double max = -1;
                int index = 1;
                for (int i = 0; i < 7; i++) {
                    double num = jo.get(i + 1 + "").getAsDouble();
                    if (num > max) {
                        max = num;
                        index = i + 1;
                    }
                }

                model.addAttribute("image", cdn_location + "/" + image);
                NumberFormat formatter = new DecimalFormat("#00.00");
                model.addAttribute("certainty", formatter.format(max*100));
                String indexStr = "akiec";
                switch (index) {
                    case 2: indexStr = "bcc";
                    break;
                    case 3: indexStr = "bkl";
                    break;
                    case 4: indexStr = "df";
                    break;
                    case 5: indexStr = "mel";
                    break;
                    case 6: indexStr = "mv";
                    break;
                    case 7: indexStr = "vasc";
                    break;
                    default:
                        break;
                }
                model.addAttribute("index", indexStr);


            }

            return "home";
        } else {
            return "redirect:/login";
        }



    }
}
