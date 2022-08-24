package com.tunv.ham10000;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class Ham10000Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Ham10000Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Ham10000Application.class, args);


    }


    public static String getMD5(String data) throws NoSuchAlgorithmException
    {
        MessageDigest messageDigest=MessageDigest.getInstance("MD5");

        messageDigest.update(data.getBytes());
        byte[] digest=messageDigest.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(Integer.toHexString((int) (b & 0xff)));
        }
        return sb.toString();
    }

}
