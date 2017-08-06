package com.zayn.controller;

import com.zayn.bean.Customers;
import com.zayn.bean.DriverServes;
import com.zayn.bean.FriendRequests;
import com.zayn.bean.Friends;
import com.zayn.repo.CustomerRepository;
import com.zayn.repo.DriverServeReppository;
import com.zayn.repo.FriendRepository;
import com.zayn.repo.FriendRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zaynr on 2017/7/14.
 */
@Controller
@RequestMapping("/friend")
public class FriendsController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private FriendRequestRepository requestRepository;
    @Autowired
    private DriverServeReppository serveReppository;

    @RequestMapping("/accept-request.do")
    @ResponseBody
    public String acceptRequest(@RequestParam("userial1") int userial1
            , @RequestParam("userial2") int userial2){
        String status = "success";
        if(friendRepository.findPerfectMatch(userial1, userial2) == null){
            Friends friend = new Friends();
            friend.setUserial1(userial1);
            friend.setUserial2(userial2);
            friendRepository.save(friend);
            requestRepository.deletePerfectMatch(userial1, userial2);
        }
        else{
            status = "fail";
        }
        return status;
    }

    @RequestMapping("/add-friend.do")
    @ResponseBody
    public String addFriend(@RequestParam("userial1") int userial1
            , @RequestParam("userial2") int userial2){
        String status = "success";
        if(requestRepository.findPerfectMatch(userial1, userial2) == null){
            FriendRequests request = new FriendRequests();
            request.setUserial1(userial1);
            request.setUserial2(userial2);
            requestRepository.save(request);
        }
        else{
            status = "fail";
        }
        return status;
    }

    @RequestMapping("/decline-request.do")
    @ResponseBody
    public void declineRequest(@RequestParam("userial1") int userial1
            , @RequestParam("userial2") int userial2){
        requestRepository.deletePerfectMatch(userial1, userial2);
    }

    @RequestMapping("/delete-friend.do")
    @ResponseBody
    public void deleteFriend(@RequestParam("userial1") int userial1
            , @RequestParam("userial2") int userial2){
        friendRepository.deletePerfectMatch(userial1, userial2);
    }

    @RequestMapping("/show-requests.do")
    @ResponseBody
    public ArrayList<FriendRequests> showRequest(@RequestParam("userial1") int userial1){
        return requestRepository.findByUserial(userial1);
    }

    @RequestMapping("/show-friends.do")
    @ResponseBody
    public ArrayList<Friends> showFriend(@RequestParam("userial1") int userial1){
        return friendRepository.findByUserial(userial1);
    }

    @RequestMapping("/get-driver-serve.do")
    @ResponseBody
    public ArrayList<DriverServes> getDriverServe(@RequestParam() Map<String, String> param){
        return serveReppository.getFriendByDriverMobileNum(param.get("rec_mobile_num"));
    }

    @RequestMapping("/update-driver-serve.do")
    @ResponseBody
    public String updateDriverServe(@RequestParam() Map<String, String> param){
        String status = null;
        ArrayList<DriverServes> serves = serveReppository.getFriendByDriverMobileNum(param.get("rec_mobile_num"));
        if (serves == null || serves.size() == 0) {
            Customers c = customerRepository.findBySerial_num(param.get("call_serial"));
            serveReppository.updateDriverServeMan(param.get("rec_mobile_num"), param.get("call_serial")
                    , c.getUser_name(), c.getMobile_number());
            status = "add new driver";
            return status;
        }
        for(DriverServes f : serves) {
            if (f.getCall_serial().equals(param.get("call_serial"))) {
                serveReppository.updateDriverServeTime(param.get("rec_mobile_num"), param.get("call_serial"));
                status = "update serve count";
            } else {
                Customers c = customerRepository.findBySerial_num(param.get("call_serial"));
                serveReppository.updateDriverServeMan(param.get("rec_mobile_num"), param.get("call_serial")
                        , c.getUser_name(), c.getMobile_number());
                status = "add new customer";
            }
        }
        return status;
    }

}
