package com.zayn.controller;

import com.zayn.bean.Orders;
import com.zayn.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by zengzy19585 on 2017/7/13.
 */
@RequestMapping("/order")
@Controller
public class OrdersController {
    @Autowired
    private OrderRepository repository;

    @RequestMapping("/get-by-serial.do")
    @ResponseBody
    private Map<String, String> getBySerial(@RequestParam("serial_num")String serial_num){
        Map<String, String> res = new HashMap<String, String>();
        Orders order = repository.findBySerial_num(serial_num);
        res.put("ori_lat", order.getOri_lat());
        res.put("ori_lng", order.getOri_lng());
        res.put("des_lat", order.getDes_lat());
        res.put("des_lng", order.getDes_lng());
        res.put("apt_time", order.getApt_time().toString());
        return res;
    }

    @RequestMapping("/get-by-rec.do")
    @ResponseBody
    private Map<String, ArrayList<String>> getByRec(@RequestParam("rec_mobile_num")String rec_mobile_num){
        Map<String, ArrayList<String>> res = new HashMap<String, ArrayList<String>>();
        for(Orders order : repository.findByRec_mobile_num(rec_mobile_num)){
            ArrayList<String> detail = new ArrayList<String>();
            detail.add(order.getOri_lat());
            detail.add(order.getOri_lng());
            detail.add(order.getDes_lat());
            detail.add(order.getDes_lng());
            detail.add(order.getApt_time().toString());
            res.put(order.getRec_mobile_num(), detail);
        }
        return res;
    }

    @RequestMapping("/get-by-call.do")
    @ResponseBody
    private Map<String, ArrayList<String>> getByCall(@RequestParam("call_serial")String call_serial){
        Map<String, ArrayList<String>> res = new HashMap<String, ArrayList<String>>();
        for(Orders order : repository.findByRec_mobile_num(call_serial)){
            ArrayList<String> detail = new ArrayList<String>();
            detail.add(order.getOri_lat());
            detail.add(order.getOri_lng());
            detail.add(order.getDes_lat());
            detail.add(order.getDes_lng());
            detail.add(order.getApt_time().toString());
            res.put(order.getCall_serial(), detail);
        }
        return res;
    }

    @RequestMapping("/place-order.do")
    @ResponseBody
    private String placeOrder(@RequestParam() Map<String, String> orderInfo){
        String status = "success!";
        Orders order = new Orders();
        Long time = Long.parseLong(orderInfo.get("apt_time"));
        order.setApt_time(new Date(time));
        order.setOri_lat(orderInfo.get("ori_lat"));
        order.setOri_lng(orderInfo.get("ori_lng"));
        order.setDes_lat(orderInfo.get("des_lat"));
        order.setDes_lng(orderInfo.get("des_lng"));
        order.setCall_serial(orderInfo.get("call_serial"));
        order.setCall_type(orderInfo.get("call_type"));
        order.setStatus("0");
        repository.save(order);
        return status;
    }
}
