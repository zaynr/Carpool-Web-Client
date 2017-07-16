package com.zayn.controller;

import com.zayn.bean.FriendRequests;
import com.zayn.bean.Friends;
import com.zayn.repo.FriendRepository;
import com.zayn.repo.FriendRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by zaynr on 2017/7/14.
 */
@Controller
@RequestMapping("/friend")
public class FriendsController {
    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private FriendRequestRepository requestRepository;

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
}
