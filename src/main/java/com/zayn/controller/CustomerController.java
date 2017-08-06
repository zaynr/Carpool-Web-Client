package com.zayn.controller;

import com.zayn.bean.Customers;
import com.zayn.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zaynr on 2017/7/11.
 */
@RequestMapping("/customer")
@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @RequestMapping("/customer-register.do")
    public String renderRegisterPage(){
        return "customer-register";
    }

    @RequestMapping("/customer-login.do")
    public String renderLoginPage(){
        return "customer-login";
    }

    @RequestMapping("/post-customer-data.do")
    @ResponseBody
    public String postRegisterData(@RequestParam() Map<String, String> userInfo){
        String status = "success!";
        for(Customers c : repository.findAll()){
            if(c.getSerial_num().equals(userInfo.get("userInfo[serial_number]"))){
                status = "false!";
                break;
            }
        }
        if(status.equals("success!")){
            Customers customers = new Customers();
            customers.setMobile_number("请设置手机号");
            customers.setSex("你猜我猜不猜");
            customers.setSerial_num(userInfo.get("userInfo[serial_number]"));
            customers.setUser_name("恒生员工" + userInfo.get("userInfo[serial_number]"));
            customers.setPwd(userInfo.get("userInfo[password]"));
            repository.save(customers);
        }
        return status;
    }

    @RequestMapping("/post-customer-data-login.do")
    @ResponseBody
    public byte[] postLoginData(@RequestParam() Map<String, String> userInfo){
        String status = "未找到用户，请先注册!";
        for(Customers c : repository.findAll()){
            if(c.getSerial_num().equals(userInfo.get("userInfo[serial_number]"))){
                if(!c.getPwd().equals(userInfo.get("userInfo[password]"))){
                    status = "密码错误!";
                    break;
                }
                status = "success!";
            }
        }
        return Charset.forName("UTF-8").encode(status).array();
    }

    @RequestMapping("/retrieve-customer-info.do")
    @ResponseBody
    public Map<String, String> retrieveCustomerInfo(@RequestParam("serial_num") String serial_num){
        Map<String, String> customerInfo = new HashMap<String, String>();
        Customers c = repository.findBySerial_num(serial_num);
        customerInfo.put("mobile_number", c.getMobile_number());
        customerInfo.put("user_name", c.getUser_name());
        customerInfo.put("sex", c.getSex());
        customerInfo.put("pwd", c.getPwd());
        return customerInfo;
    }

    @RequestMapping("/update-password.do")
    @ResponseBody
    public String updatePassword(@RequestParam("serial_num") String serial_num, @RequestParam("old_pwd") String oldPwd
            , @RequestParam("new_pwd") String newPwd){
        String status = "success";
        Customers c = repository.findBySerial_num(serial_num);
        if(c.getPwd().equals(oldPwd)){
            repository.updatePwd(serial_num, newPwd);
        }
        else{
            status = "mismatch";
        }
        return status;
    }

    @RequestMapping("/update-user-info.do")
    @ResponseBody
    public String updateUserInfo(@RequestParam() Map<String, String> param){
        String status = "success";
        repository.updateCustomerInfo(param.get("serial_num"), param.get("sex")
                ,param.get("mobile_number"), param.get("user_name"));
        return status;
    }
}
