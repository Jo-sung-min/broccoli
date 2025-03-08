package com.project.broccoli.service;

import com.project.broccoli.domain.UserInfo;
import com.project.broccoli.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getAllUsers() {
        return userInfoMapper.findAll();
    }
}
