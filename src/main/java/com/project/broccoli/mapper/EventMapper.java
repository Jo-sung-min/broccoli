package com.project.broccoli.mapper;

import com.project.broccoli.domain.ContentsIfoDTO;
import com.project.broccoli.domain.Event;
import com.project.broccoli.domain.EventComm;
import com.project.broccoli.domain.EventWinnerContact;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface EventMapper {
    // 이벤트 참여 정보 확인
    int findEvtApply(Map<String, Object> maps);
    EventComm findApplyInfo(Map<String, Object> maps);
    int findMyWinCount(Map<String, Object> maps);
    EventWinnerContact findWinMyInfo(Map<String, Object> maps);
    Event findEventOne(Map<String, Object> maps);

    void saveAgreeInfo(Map<String, Object> maps);
    void updateAgreeInfo(Map<String, Object> maps);


    // /////////////////////////////////////////////////////////////
    // 시즌 이벤트 관련 로직
    List<Map> seriesLikeCnt(Map<String, Object> maps);
    int bounsChk(Map<String, Object> maps);
    void saveEvtStudyHist(Map<String, Object> maps);
    List<ContentsIfoDTO> todayContsInfo(Map<String, Object> maps);
    List<Map> findSeriesEvtHistCnt(Map<String, Object> maps);
    void updateEventViewCount(Map<String, Object> maps);


    // /////////////////////////////////////////////////////////////
    // 이벤트 학습 관련 로직
    int findTodayCompleteCount(Map<String, Object> maps);




}
