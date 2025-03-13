package com.project.broccoli.controller;

import com.project.broccoli.domain.UserInfo;
import com.project.broccoli.mapper.MemberMapper;
import com.project.broccoli.pot.util.PotSessionUtils;
import com.project.broccoli.pot.util.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    final private MemberMapper memberMapper;


    @RequestMapping("/saveUserInfo")
    public String saveUserInfo(
            final HttpServletResponse response,
            final HttpServletRequest request,
            final UserInfo userInfo,
            @RequestParam final Map<String, Object> maps) throws Exception {

        log.debug("==========================================");
        log.debug("파라미터 확인 maps {}  =================",maps);
        log.debug("==========================================");
        Boolean existId = memberMapper.isExistId(maps);
        if(existId){
            // 신규 회원
            memberMapper.saveMember(maps);
        }else {
            // 회원 정보 업데이트
            memberMapper.updateMember(maps);
        }

        return "redirect:/";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> Login(
            final HttpServletResponse response,
            final HttpServletRequest request,
            @RequestParam final Map<String, Object> maps) throws Exception {

        // 회원테이블 체크
        final String chkId = memberMapper.findLoginInfo();

        if ("".equals(Utils.NUllCheck(chkId))) {
            maps.put("result", "1");
        } else {
            // 패스워드 체크
            final String encrptedPwd = maps.get("p_password").toString();

            maps.put("pwd", encrptedPwd);
            final UserInfo loginUserInfo = memberMapper.findUserLoginCheckPwd(maps);
            if (loginUserInfo == null) {
                maps.put("result", "2");
            } else {
                // 로그인 성공 로직
                maps.put("result", "0");

                // 로그인 시 세션 ID 초기화
                legacySessionRemove(request);

                request.getSession().setAttribute("ssoUserProfile", request.getSession(true).getAttribute("ssoUserProfile"));
                request.getSession().setAttribute("EBSM_USER_INFO", loginUserInfo);
                request.getSession().setAttribute("COMM_USER_INFO", loginUserInfo.toString());

                if ("localhost".equals(request.getServerName())) {
//                    request.getSession().getServletContext().getContext("/").setAttribute("COMM_USER_INFO",loginUserInfo.toQueryString());
                }
            }
        }
        return maps;
    }


    /**
     * 기존 세션 로그인 해제
     */
    private void legacySessionRemove(final HttpServletRequest request) {
        final HttpSession legacySession = request.getSession(false);

        if (legacySession != null && !legacySession.isNew()) {
            legacySession.invalidate();
        }

    }



}
