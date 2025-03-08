package com.project.broccoli.controller;

import com.project.broccoli.domain.UserInfo;
import com.project.broccoli.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@Slf4j
public class HomeController {

    @Autowired
    UserInfoService userInfoService;


    @RequestMapping("/")
    public String myHome(){

        List<UserInfo> test =  userInfoService.getAllUsers();

        log.info("호출 !!!!!!!!!!!!{}!",test);
        log.info("호출 !!!!!!!!!!!!{}!",test);


        return "home.html";

    }

}
