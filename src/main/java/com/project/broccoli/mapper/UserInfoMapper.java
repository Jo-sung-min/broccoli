package com.project.broccoli.mapper;


import com.project.broccoli.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserInfoMapper {

    List<UserInfo> findAll();

}
