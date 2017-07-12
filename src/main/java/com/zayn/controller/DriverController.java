package com.zayn.controller;

import com.zayn.bean.Drivers;
import com.zayn.repo.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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
    public String renderPage(){
        return "driver-register";
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
            driver.setCar_plate(userInfo.get("userInfo[mobile_number]"));
            driver.setDriver_name(userInfo.get("userInfo[name]"));
            driver.setPwd(userInfo.get("userInfo[password]"));
            repository.save(driver);
        }
        return status;
    }

    @RequestMapping("/getting-driver-loc.do")
    @ResponseBody
    public Map<String, String> getLoc(@RequestParam(name = "mobile_number")
                                                  String mobile_number){
        Map<String, String> loc = new HashMap<String, String>();
        Drivers driver = repository.findByMobile_number(mobile_number);
        String lat,lng;
        if(driver!=null){
            lat = driver.getLat();
            lng = driver.getLng();
            lat = lat + ";" + lng;
            loc.put("location", lat);
        }
        return loc;
    }
}
