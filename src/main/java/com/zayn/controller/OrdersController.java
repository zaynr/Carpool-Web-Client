package com.zayn.controller;

import com.zayn.bean.Customers;
import com.zayn.bean.Orders;
import com.zayn.repo.CustomerRepository;
import com.zayn.repo.DriverRepository;
import com.zayn.repo.OrderRepository;
import com.zayn.util.GetDistanceUtil;
import com.zayn.util.LatLng;
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
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/confirm-order.do")
    @ResponseBody
    public void confirmOrder(@RequestParam("serial_num")int serial_num, @RequestParam("rec_mobile_num")
            String rec_mobile_num){
        repository.recOrder(serial_num, rec_mobile_num);
    }

    @RequestMapping("/cancel-order.do")
    @ResponseBody
    public void cancelOrder(@RequestParam("serial_num")int serial_num){
        repository.cancleOrder(serial_num);
    }

    @RequestMapping("/get-dispatched.do")
    @ResponseBody
    public Orders getDispatched(@RequestParam("rec_mobile_num") String rec_mobile_num){
        double ori_lat, ori_lng;
        LatLng ori, dest;
        ori_lat = Double.parseDouble(driverRepository
                .findByMobile_number(rec_mobile_num).getLat());
        ori_lng = Double.parseDouble(driverRepository
                .findByMobile_number(rec_mobile_num).getLng());
        ori = new LatLng(ori_lat, ori_lng);
        double cur_dis = Long.MAX_VALUE;
        int cur_serial = 0;
        GetDistanceUtil util;
        for(Orders order : repository.findAllUndone()){
            dest = new LatLng(Double.parseDouble(order.getOri_lat()), Double.parseDouble(order.getOri_lng()));
            util = new GetDistanceUtil(ori, dest);
            if(cur_dis > util.getDistance()){
                cur_dis = util.getDistance();
                cur_serial = order.getSerial_num();
            }
        }
        repository.recOrder(cur_serial, rec_mobile_num);
        return repository.findBySerial_num(cur_serial);
    }

    @RequestMapping("/get-by-serial.do")
    @ResponseBody
    public ArrayList<HashMap<String, String>> getBySerial(@RequestParam("serial_num")int serial_num){
        ArrayList<HashMap<String, String>> res = new ArrayList<HashMap<String, String>>();
        Orders order = repository.findBySerial_num(serial_num);
        res = packResultSet(order, res);
        return res;
    }

    @RequestMapping("/get-by-rec.do")
    @ResponseBody
    public ArrayList<HashMap<String, String>> getByRec(@RequestParam("rec_mobile_num")String rec_mobile_num){
        ArrayList<HashMap<String, String>> res = new ArrayList<HashMap<String, String>>();
        for(Orders order : repository.findByRec_mobile_num(rec_mobile_num)){
            res = packResultSet(order, res);
        }
        return res;
    }

    @RequestMapping("/get-all-undone-order.do")
    @ResponseBody
    public ArrayList<HashMap<String, String>> getAllUndone(){
        ArrayList<HashMap<String, String>> res = new ArrayList<HashMap<String, String>>();
        for(Orders order : repository.findAllUndone()){
            res = packResultSet(order, res);
        }
        return res;
    }

    private ArrayList<HashMap<String, String>> packResultSet(Orders order, ArrayList<HashMap<String, String>> res){
        HashMap<String, String> detail = new HashMap<String, String>();
        detail.put("status", order.getStatus());
        detail.put("customer_comment", order.getCustomer_comment());
        detail.put("rating", order.getRating());
        detail.put("call_serial", order.getCall_serial());
        detail.put("rec_mobile_num", order.getRec_mobile_num());
        detail.put("ori_lat", order.getOri_lat());
        detail.put("ori_lng", order.getOri_lng());
        detail.put("des_lat", order.getDes_lat());
        detail.put("des_lng", order.getDes_lng());
        detail.put("des_address", order.getDes_address());
        detail.put("ori_address", order.getOri_address());
        detail.put("apt_time", order.getApt_time().toString());
        detail.put("serial_num", String.valueOf(order.getSerial_num()));
        Customers c = customerRepository.findBySerial_num(order.getCall_serial());
        detail.put("customer_name", c.getUser_name());
        detail.put("customer_mobile_number", c.getMobile_number());
        res.add(detail);
        return res;
    }

    @RequestMapping("/get-by-call.do")
    @ResponseBody
    public ArrayList<HashMap<String, String>> getByCall(@RequestParam("call_serial")String call_serial){
        ArrayList<HashMap<String, String>> res = new ArrayList<HashMap<String, String>>();
        for(Orders order : repository.findByCall_serial(call_serial)){
            res = packResultSet(order, res);
        }
        return res;
    }

    @RequestMapping("/place-order.do")
    @ResponseBody
    public String placeOrder(@RequestParam() Map<String, String> orderInfo){
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
        order.setOri_address(orderInfo.get("ori_address"));
        order.setDes_address(orderInfo.get("des_address"));
        order.setStatus("0");
        repository.save(order);
        return status;
    }
}
