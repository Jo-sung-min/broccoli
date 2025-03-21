package com.project.broccoli.controller;

import com.project.broccoli.mapper.EventMapper;
import com.project.broccoli.pot.util.ClassLoader;
import com.project.broccoli.pot.util.EventUtils;
import com.project.broccoli.pot.util.PotSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "EventAjax")
public class EventAjaxController {

    final EventMapper eventMapper;


    /**
     * 이벤트 참가 신청 정보 저장
     * @param model
     * @param maps
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/eventAppSaveAttend")
    @ResponseBody
    public Map<String, Object> eventAppSaveAttend(
            ModelMap model,
            @RequestParam Map<String,Object> maps) throws Exception{
        final Map<String , Object> jsonObject = ClassLoader.loadMap();

        if (PotSessionUtils.isLogin()) {
            boolean isApply = false;
            eventLoginSetting(maps);

            isApply = eventMapper.findEvtApply(maps) > 0;
            if(isApply){
                eventMapper.updateAgreeInfo(maps);
            }else{
                eventMapper.saveAgreeInfo(maps);
            }
            jsonObject.put("result", "success");
        }else{
            jsonObject.put("result", "needLogin");
        }
        return jsonObject;
    }


    /**
     * 이벤트 학습이력 저장
     * @param model
     * @param maps
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/eventAppSaveSeason")
    @ResponseBody
    public Map<String, Object> eventAppSaveSeason(
            ModelMap model,
            @RequestParam Map<String, Object> maps) throws Exception{

        final Map<String, Object> jsonObject = ClassLoader.loadMap();

        if (PotSessionUtils.isLogin()) {
            eventLoginSetting(maps);

            final boolean isApply = eventMapper.findEvtApply(maps) > 0;
            if (isApply) {
                final int todayCompleteCount = eventMapper.findTodayCompleteCount(maps);
                //하루에 몇개 학습 할 수 있는지 세팅
                if (todayCompleteCount < 1) {
                    maps.put("add10", "STUDY_HIST");
                    eventMapper.saveEvtStudyHist(maps);
                    jsonObject.put("result", "success");
                }
                jsonObject.put("saveAferTodayCompleteCount", todayCompleteCount);
            }
        } else {
            jsonObject.put("result", "needLogin");
        }
        return jsonObject;
    }


    /**
     * 이벤트 학습이력 저장
     * @param model
     * @param maps
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findAttendStampEvtList" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> findAttendStampEvtLIst(
            ModelMap model,
            @RequestParam Map<String, Object> maps) throws Exception{

        final Map<String, Object> jsonObject = ClassLoader.loadMap();

        if (PotSessionUtils.isLogin()) {
            eventLoginSetting(maps);

            final boolean isApply = eventMapper.findEvtApply(maps) > 0;
            if (isApply) {
                final int todayCompleteCount = eventMapper.findTodayCompleteCount(maps);
                //하루에 몇개 학습 할 수 있는지 세팅
                if (todayCompleteCount < 1) {
                    maps.put("add10", "STUDY_HIST");
                    eventMapper.saveEvtStudyHist(maps);
                    jsonObject.put("result", "success");
                }
                jsonObject.put("saveAferTodayCompleteCount", todayCompleteCount);
            }
        } else {
            jsonObject.put("result", "needLogin");
        }
        return jsonObject;
    }









    
    // 로그인 확인되면 로그인정보 세팅하는 함수
    private static void eventLoginSetting(Map<String, Object> maps) {
        EventUtils eventCode = EventUtils.getInstance();

        final String userId = PotSessionUtils.getUesrInfo().getUserId();
        boolean isAdmin= false;
        isAdmin = EventUtils.isEventAdmin(userId);
        String paramDt = EventUtils.getTodayByYYYYMMDD("yyyymmdd");
        paramDt = isAdmin && maps.containsKey("paramDt") && !"".equals(maps.get("paramDt")) ? maps.get("paramDt").toString() : paramDt;

        maps.put("gbnCd", eventCode.getAttendCode());
        // 1.관리자 일 때 이벤트 테스트코드로 세팅        
        if (isAdmin) {
            maps.put("gbnCd", eventCode.getAttendTestCode());
        }
        // 2.유저 아이디 세팅
        maps.put("userId", userId);
        // 3.일자 세팅 관리자면 테스트 일자로
        maps.put("paramDt", paramDt);


    }

}
