package com.project.broccoli.session;

import com.project.broccoli.domain.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {

    HttpServletRequest request = new MockHttpServletRequest();
    HttpServletResponse response = new MockHttpServletResponse();

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {

        UserInfo userInfo = new UserInfo();
        //세션 생성
        sessionManager.createSession(userInfo,response);
    }




}