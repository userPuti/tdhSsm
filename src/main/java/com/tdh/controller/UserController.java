package com.tdh.controller;

import com.tdh.domain.User;
import com.tdh.service.UserService;
import com.tdh.service.impl.ImplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @author Puti
 * @date 2022/4/9 10:52
 */
@Controller
public class UserController {

//    @Autowired
    private UserService userService = new ImplUserService();

    @RequestMapping("/hello")
    @ResponseBody
    public String test() {
        List<User> users = userService.selectAllUser();
        return users.toString();
    }
}
