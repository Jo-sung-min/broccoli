package com.project.broccoli.controller;

import com.project.broccoli.pot.util.EventUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller(value = "/Event")
public class EventController {

    public String attendEvtMain(
            final HttpServletResponse response,
            final HttpServletRequest request,
            final ModelMap model,
            @RequestParam Map<String, Object> maps) {

        boolean isApply = false; // 이벤트 가입여부
        boolean isAdmin = false; // 관리자 여부
        List<Map> studyHist = null; // 학습여부

        String winYn = "N";
        String goods = "";
        String paramDt = EventUtils.getTodayByYYYYMMDD("yyyyMMdd");


    return "";
    }


    public Map<String, Object> paper(int idx) {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> pMap1 = new HashMap<>();
        pMap1.put("pid", "1");
        pMap1.put("question", "영상속 피보나치 수열을 설명하는 악기는?");
        pMap1.put("i1", "피아노1");
        pMap1.put("i2", "피아노2");
        pMap1.put("i3", "피아노3");
        pMap1.put("i4", "피아노4");
        pMap1.put("correct", "1");

        Map<String, Object> pMap2 = new HashMap<>();
        pMap1.put("pid", "2");
        pMap1.put("question", "영상속 피보나치2 수열을 설명하는 악기는?");
        pMap1.put("i1", "첼로");
        pMap1.put("i2", "첼로5");
        pMap1.put("i3", "첼로4");
        pMap1.put("i4", "첼로3");
        pMap1.put("correct", "2");

        list.add(0, pMap1);
        list.add(1, pMap2);

        return list.get(idx);


    }

}
