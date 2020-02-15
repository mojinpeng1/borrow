package com.mjp.borrow.utils;

import com.mjp.borrow.model.AccoutInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>Description：控制层工具类</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/18 17:52
 */
public class ControllerUtils {
    final static String ACCOUNT_INFO = "account_info";

    public static AccoutInfo getCurAccount(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(ControllerUtils.ACCOUNT_INFO);
        return (AccoutInfo) attribute;

    }

    public static void setCurAccount(HttpServletRequest request, AccoutInfo accoutInfo) {
        HttpSession session = request.getSession();
        session.setAttribute(ControllerUtils.ACCOUNT_INFO, accoutInfo);


    }
}
