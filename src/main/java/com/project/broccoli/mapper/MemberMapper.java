package com.project.broccoli.mapper;

import com.project.broccoli.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MemberMapper {
    public String findLoginInfo();

    public UserInfo findUserLoginCheckPwd(Map<String, Object> maps);

    public Boolean isExistId(Map<String, Object> maps);

    public void saveMember(Map<String, Object> maps);

    public void updateMember(Map<String, Object> maps);
}
