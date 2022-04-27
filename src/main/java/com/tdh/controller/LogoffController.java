package com.tdh.controller;

import com.tdh.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author Puti
 * @date 2022/4/11 9:45
 */
@Controller
public class LogoffController {

    private static final Log log = LogFactory.getLog(LogoffController.class);

    @Autowired
    private UserService userService;

    /**
     * 注销
     *
     * @param session session对象
     * @return 返回到登录页面
     */
    @RequestMapping("/logoff")
    @ResponseBody
    public String logoff(HttpSession session) {
        String username = (String) session.getAttribute("username");
        log.info("管理员" + username + "注销");
        session.invalidate();
        return "/";
    }
}
