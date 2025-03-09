package com.project.broccoli.controller;

import com.project.broccoli.domain.UserInfo;
import com.project.broccoli.service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserInfoService userInfoService;

    @RequestMapping("/")
    public String myHome(Model model, HttpServletResponse response, HttpServletRequest request){


        List<UserInfo> test =  userInfoService.getAllUsers();


        model.addAttribute("uerInfo",test);

        return "home";
    }











}
