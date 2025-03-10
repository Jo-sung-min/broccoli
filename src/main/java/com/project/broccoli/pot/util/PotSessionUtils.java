package com.project.broccoli.pot.util;

import com.project.broccoli.domain.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@SuppressWarnings("UNALIDATED_REDIRECT")
public class PotSessionUtils {

    private static final String USER_SESSION_KEY = "USER_INFO";

    private static final String ADMIN_USER_SESSION_KEY = "ADMIN_USER_INFO";

    /**
     * httpServletRequest 반환
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * reqlPath 반환
     * @return
     */
    public static String getRealPath() {
        return getHttpServletRequest().getSession().getServletContext().getRealPath("");
    }

    /**
     * ContextPath 반환
     * @return
     */
    public static String getContextPath(){
        return getHttpServletRequest().getSession().getServletContext().getContextPath();
    }

    /**
     * 관리자 세션 빈 반환
     * @return
     */
    public static UserInfo getAdminUesrInfo() {
        return (UserInfo) getHttpServletRequest().getSession().getAttribute(ADMIN_USER_SESSION_KEY);
    }

    /**
     * 사용자 세션 빈 반환
     * @return
     */
    public static UserInfo getUesrInfo() {
        return (UserInfo) getHttpServletRequest().getSession().getAttribute(USER_SESSION_KEY);
    }

    /**
     * 로그인 여부 반환
     * @return
     */
    public static boolean isLogin() {
        return getUesrInfo() != null && getUesrInfo().getUserId() != null; 
    }

    /**
     * 관리자 여부 반환
     * @return
     */
    public static boolean isAdmin() {
        return isLogin() && "Y".equals(getUesrInfo().getIsAdmin());
    }

    /**
     * 로그아웃
     */
    public static void logout(){
        getHttpServletRequest().getSession().invalidate();
    }

    /**
     * 메인페이지로 강제 이동
     * @param response
     * @param messageName
     * @throws Exception
     */
    public static void goMainPage(final HttpServletResponse response, final String messageName) throws Exception {
        urlRedirectAndAlert(response,getContextPath()+"/template",messageName);
    }
    
    /**
     *  urlRedirectAndAlert
     * @param response
     * @param url
     * @param messageName
     */
    public static void urlRedirectAndAlert(final HttpServletResponse response, final String url, final String messageName) throws Exception {
        response.sendRedirect("/potcom/redirectUrl?url="+url+"&GLOBAL_ALERT_MESSAGE="+messageName);
    }


    /**
     *  urlRedirectAndCallFunction
     * @param response
     * @param url
     * @param functionName
     */
    public static void urlRedirectAndCallFunction(final HttpServletResponse response, final String url, final String functionName) throws Exception {
        response.sendRedirect("/potcom/redirectUrl?url="+url+"&GLOBAL_CALL_BACK="+functionName);
    }
    


}
