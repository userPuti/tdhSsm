package com.tdh.controller;

import com.tdh.service.UserService;
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
    @Autowired
    private UserService userService;

    @RequestMapping("/logoff")
    @ResponseBody
    public String logoff(HttpSession session) {
        session.invalidate();
        return "/";
    }
}
