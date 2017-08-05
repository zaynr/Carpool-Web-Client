package com.zayn.controller;

import com.zayn.bean.Customers;
import com.zayn.bean.Drivers;
import com.zayn.repo.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zengzy19585 on 2017/7/12.
 */
@RequestMapping("/driver")
@Controller
public class DriverController {

    @Autowired
    private DriverRepository repository;

    @RequestMapping("/driver-register.do")
    public String renderRegisterPage(){
        return "driver-register";
    }

    @RequestMapping("/driver-login.do")
    public String renderLoginPage(){
        return "driver-login";
    }

    @RequestMapping("/retrieve-driver-info.do")
    @ResponseBody
    public Drivers retrieveDriverInfo(@RequestParam() Map<String, String> userInfo){
        Drivers res;
        res = repository.findByMobile_number(userInfo.get("mobile_number"));
        return res;
    }

    @RequestMapping("/post-register-data.do")
    @ResponseBody
    public String postData(@RequestParam() Map<String, String> userInfo){
        String status = "success!";
        for(Drivers drivers : repository.findAll()){
            if(drivers.getMobile_number().equals(userInfo.get("userInfo[mobile_number]"))){
                status = "false!";
                break;
            }
        }
        if(status.equals("success!")){
            Drivers driver = new Drivers();
            driver.setMobile_number(userInfo.get("userInfo[mobile_number]"));
            driver.setCar_plate("请设置车牌号");
            driver.setDriver_name("司机用户");
            driver.setRating(5.0f);
            driver.setPwd(userInfo.get("userInfo[password]"));
            repository.save(driver);
        }
        return status;
    }

    @RequestMapping("/post-driver-data-login.do")
    @ResponseBody
    public byte[] postLoginData(@RequestParam() Map<String, String> userInfo){
        String status = "未找到用户，请先注册!";
        for(Drivers c : repository.findAll()){
            if(c.getMobile_number().equals(userInfo.get("userInfo[mobile_number]"))){
                if(!c.getPwd().equals(userInfo.get("userInfo[password]"))){
                    status = "密码错误!";
                    break;
                }
                status = "success!";
            }
        }
        return Charset.forName("UTF-8").encode(status).array();
    }

    @RequestMapping("/getting-driver-loc.do")
    @ResponseBody
    public Map<String, List<String>> getLoc(@RequestParam(name = "mobile_number")
                                                  String mobile_number){
        Drivers driver = repository.findByMobile_number(mobile_number);
        Map<String, List<String>> res = new HashMap<String, List<String>>();
        List<String> loc = new ArrayList<String>();
        loc.add(driver.getLat());
        loc.add(driver.getLng());
        res.put(driver.getMobile_number(), loc);
        return res;
    }

    @RequestMapping("/getting-all-driver-loc.do")
    @ResponseBody
    public Map<String, List<String>> getAllLoc(){
        Map<String, List<String>> res = new HashMap<String, List<String>>();
        for(Drivers driver : repository.findAll()){
            List<String> loc = new ArrayList<String>();
            loc.add(driver.getLat());
            loc.add(driver.getLng());
            res.put(driver.getMobile_number(), loc);
        }
        return res;
    }

    @RequestMapping("/updating-driver-loc.do")
    @ResponseBody
    public void updateLoc(@RequestParam(name = "mobile_number")String mobile_number,
                          @RequestParam("lat")String lat,@RequestParam("lng")String lng){
        repository.updateDriverLoc(lat,lng,mobile_number);
    }

    @RequestMapping("/update-user-info.do")
    @ResponseBody
    public void updateInfo(@RequestParam() Map<String, String> param){
        repository.updateDriverInfo(param.get("driver_name"), param.get("car_plate"), param.get("mobile_number"));
    }


    @RequestMapping("/update-password.do")
    @ResponseBody
    public String updatePassword(@RequestParam() Map<String, String> param){
        String status = "success";
        Drivers c = repository.findByMobile_number(param.get("mobile_number"));
        if(c.getPwd().equals(param.get("old_pwd"))){
            repository.updateDriverPwd(param.get("new_pwd"), param.get("mobile_number"));
        }
        else{
            status = "mismatch";
        }
        return status;
    }
}
