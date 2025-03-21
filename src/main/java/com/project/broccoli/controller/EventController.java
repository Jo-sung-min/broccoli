package com.project.broccoli.controller;

import com.project.broccoli.domain.ContentsIfoDTO;
import com.project.broccoli.domain.Event;
import com.project.broccoli.domain.EventComm;
import com.project.broccoli.domain.EventWinnerContact;
import com.project.broccoli.mapper.EventMapper;
import com.project.broccoli.pot.util.EventUtils;
import com.project.broccoli.pot.util.PotSessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller(value = "/Event")
public class EventController {

    final EventMapper eventMapper;

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

        maps.put("gbnCh", "150");
        // 유입 경로  웹로그 로직 구현용
        String query = PotSessionUtils.getHttpServletRequest().getQueryString();
        query = query == null ? "" : query;
        maps.put("query", query);

        if (PotSessionUtils.isLogin()) {
            isApply = eventMapper.findEvtApply(maps) > 0;
            isAdmin = EventUtils.isEventAdmin(PotSessionUtils.getUesrInfo().getUserId());
            EventComm evtApplyInfo = eventMapper.findApplyInfo(maps);

            maps.put("userId", PotSessionUtils.getUesrInfo().getUserId());
            maps.put("evtApplyInfo", evtApplyInfo);
            if(isAdmin){
                maps.put("isAdmin", true);
            }

            boolean isUpdate = evtApplyInfo != null;

            model.addAttribute("evtApplyInfo", evtApplyInfo);
            model.addAttribute("isUpdate", isUpdate);

            // 동의 팝업용E
            // 당첨여부 조희
            winYn = eventMapper.findMyWinCount(maps) > 0 ? "Y" : "N";
            if ("Y".equals(winYn)) {
                EventWinnerContact contact = eventMapper.findWinMyInfo(maps);
                if (contact != null) {
                    goods = contact.getAdd2();
                    model.addAttribute("contact", contact);
                }
            }
        }
        paramDt = isAdmin && maps.containsKey("paramDt") && !"".equals(maps.get("paramDt")) ? maps.get("paramDt").toString() : paramDt;
        maps.put("paramDt", paramDt);
        maps.put("isApply", isApply);

        if(isApply){
            // 보너스 이벤트 관련 처리
            final List<Map> seriesCnt = eventMapper.seriesLikeCnt(maps);
            final int bonusChk = eventMapper.bounsChk(maps);

            if (Integer.parseInt(seriesCnt.get(0).get("Vlog").toString()) >= 5) {
                maps.put("add10","BONUS");
                eventMapper.saveEvtStudyHist(maps);
            }

            // 시리즈 학습해야할 것 ///////////////////////////////////////
            // 32 개 시리즈 담는 쿼리
            List<ContentsIfoDTO> seriesList = eventMapper.todayContsInfo(maps);
            model.addAttribute("seriesList", seriesList);
            // 학습 완료 한것
            // 당일 학습 체크
            maps.put("add10", "QUIZ_HIST");
            studyHist = eventMapper.findSeriesEvtHistCnt(maps);
            int curP = 0 ;          //현재 보여줄 문제 번호

            if (null != studyHist && studyHist.size() > 0) {
                curP = Integer.parseInt(studyHist.get(0).get("curP").toString());
            }
            model.addAttribute("curp", curP + 1);

        }

        eventMapper.updateEventViewCount(maps);

        String url = eventPageUrl(model, maps, isAdmin, "marSeasonEvtMain", "marSeasonEvtEnd");

        model.addAttribute("maps", maps);
        model.addAttribute("winYn", winYn);
        model.addAttribute("goods", goods);
        model.addAttribute("paramDt", paramDt);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isApply", isApply);
        model.addAttribute("currentDate", EventUtils.getTodayByYYYYMMDD("yyyyMMddHHmmss"));

        return url;
    }


    private String eventPageUrl(ModelMap model, Map<String, Object> maps, boolean isAdmin, String mainPageName, String endPageName) {
        String url = "";
        Event event = eventMapper.findEventOne(maps);
        String beforeEndYmd = maps.containsKey("mainOpen") ? null : event.getBeforeEndYmd();
        maps.put("winnerSelectEndYmd", event.getWinnerSelectEntYmd());

        if (beforeEndYmd != null && EventUtils.isAfterThanCurrentTimestamp(beforeEndYmd)) {
            url = "front2024/2025Event/" + mainPageName;
        } else {
            if (EventUtils.isAfterThanCurrentTimestamp(event.getEndYmd())) {
                if (EventUtils.isAfterThanCurrentTimestamp(event.getEvtEndYmd())) {
                    url = "front2024/2025Event/" + mainPageName;
                } else {
                    url = "front2024/2025Event/" + endPageName;
                }
            }
        }
        return url;
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
