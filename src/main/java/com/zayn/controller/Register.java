package com.zayn.controller;

import com.zayn.bean.Customers;
import com.zayn.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by zaynr on 2017/7/11.
 */

@RequestMapping("/test")
@Controller
public class Register {
    @Autowired
    private CustomerRepository repository;

    @RequestMapping("/register.do")
    public String renderPage(Model model){
        for(Customers c : repository.findAll()){
            model.addAttribute("test", c.getUser_name());
        }
        return "register";
    }

    @RequestMapping("/post-data.do")
    @ResponseBody
    public String postData(@RequestParam() Map<String, String> userInfo){
        System.out.println(userInfo.get("userInfo[password]"));
        String status = "success!";
        return status;
    }
}
