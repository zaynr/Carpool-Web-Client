package com.zayn.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zaynr on 2017/7/11.
 */

@RequestMapping("/test")
@Controller
public class Register {

    @RequestMapping("/register.do")
    public String renderPage(){
        return "register";
    }

    @RequestMapping("/post-data.do")
    @ResponseBody
    public String postData(String encrypt){
        System.out.println(encrypt);
        String status = "success!";
        return status;
    }
}
