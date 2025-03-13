package com.project.broccoli.pot.util;

import org.thymeleaf.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.YearMonth;

public class EventUtils {
    private String EVT_MONTH;
    private String ATTEND_EVT_SNO;          // 진행중인 출석 이벤트
    private String SEASON_EVT_SNO;          // 진행중인 시즌 이벤트
    private String ATTEND_TEST_EVT_SNO;     // 개발중인 출석 이벤트 관리자용

    // 테스트 관리자 아이디
    private static final String[] COMMON_EVT_ADMIN_IDS ={
            "iwngs1761"
    };

    private EventUtils(String evtMonth, String attendTestEvtSno, String attendEvtSno, String seasonEvtSno) {

        EVT_MONTH = evtMonth;
        ATTEND_EVT_SNO = attendTestEvtSno;
        ATTEND_TEST_EVT_SNO = attendEvtSno;

        SEASON_EVT_SNO = seasonEvtSno;
    }

    private static EventUtils eventUtils = null;

    public static EventUtils getInstance() {
        String evtMonth = getTodayByYYYYMMDD("yyyyMM");
        if (eventUtils == null || !StringUtils.equals(eventUtils.EVT_MONTH, evtMonth)) {
            if ("202412".equals(evtMonth)) {
                EventUtils.eventUtils = new EventUtils(evtMonth, "164", "164", "164");
            } else if ("202501".equals(evtMonth)) {
                EventUtils.eventUtils = new EventUtils(evtMonth, "164", "164", "164");
            }else{
                EventUtils.eventUtils = new EventUtils(evtMonth, "164", "164", "164");
            }
        }
        return EventUtils.eventUtils;
    }



    public static boolean isEventAdmin(String userId){
        boolean result =false;
        result = isInclude(COMMON_EVT_ADMIN_IDS, userId);
        return result;
    }

    /**
     * 문자열 배열에서 특정 문자열 검색
     */
    public static boolean isInclude(String[] list, String str){
        boolean result = false;

        for (String tarStr : list) {
            if (str.equals(tarStr)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 날짜 유틸 
     * 오늘 날짜를 입력된 형식의 문자열로 반환 
     * YYYYMM,YYYYMMDD 모든형식 가능
     */
    public static String getTodayByYYYYMMDD(String formatStr){
        return new java.text.SimpleDateFormat(formatStr).format(new java.util.Date());
    }

    /**
     * 년도 월을 받아 해당 월의 일자를 반환
     * month 1 or 01 같은 결과로 받음
     *
     * @return 2023년 1월 > return 31일
     */
    public static int getDaysInMonth(int year, int month) {
        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();

    }

    /**
     * 인자값이 현재시간 보다 이후 시간인지 체크
     * compareTo
     * currentTimeStamp - timeStampStr
     */
    public static boolean isAfterThanCurrentTimestamp(String timeStampStr) {
        boolean result = false;

        final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentTimeStamp = format.format(new java.util.Date());
        if (currentTimeStamp.compareTo(timeStampStr) < 0) {
            result = true;
        }
        return result;
    }


    /**
     * 이벤트 진행 여부 확인(시작일, 종료일)
     */
    public static boolean isEvtPeriod(String strTimeStapStr , String endTimeStampStr){
        final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String currTimestapStr = format.format(new java.util.Date());

        // 오버로딩
        return isEvtPeriod(strTimeStapStr, endTimeStampStr, currTimestapStr);
    }
    public static boolean isEvtPeriod(String strTimesStapStr , String endTimeStampStr,String currTimeStapStr){
        boolean result = false;

        double str = Double.parseDouble(strTimesStapStr);
        double end = Double.parseDouble(strTimesStapStr);
        double cur = Double.parseDouble(strTimesStapStr);

        if (str <= cur && cur <= end) {
            result = true;
        }
        // 오버로딩
        return result;
    }



}
