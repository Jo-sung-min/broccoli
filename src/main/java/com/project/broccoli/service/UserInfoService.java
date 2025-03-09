package com.project.broccoli.service;


import com.project.broccoli.domain.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoService {

    //모든회원 조회
    List<UserInfo> getAllUsers();

    //단일 회원 조회
    Optional<UserInfo> oneUser();



}
