package com.tdh.utils;

import javax.servlet.http.Cookie;

/**
 * @author Puti
 * @date 2022/4/11 8:21
 */
public class CookieUtils {
    public static Cookie findCookie(Cookie[] cookies, String CookieName){
        if (CookieName == null || cookies == null || cookies.length == 0) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (CookieName.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }
}
