package com.project.broccoli.service;

import com.project.broccoli.domain.UserInfo;
import com.project.broccoli.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getAllUsers() {
        return userInfoMapper.findAll();
    }

    @Override
    public Optional<UserInfo> oneUser() {

        return getAllUsers().stream()
                .filter(m -> m.getUSER_NM().equals("성민"))
                .findFirst();
    }
}
