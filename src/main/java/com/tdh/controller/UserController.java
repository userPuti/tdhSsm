package com.tdh.controller;

import com.tdh.domain.Depart;
import com.tdh.domain.User;
import com.tdh.dto.YhxxDto;
import com.tdh.service.DepartService;
import com.tdh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @author Puti
 * @date 2022/4/9 10:52
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("departService")
    private DepartService departService;

    @RequestMapping("/login")
    public String login(@RequestParam("zh") String yhid, @RequestParam("kl") String yhkl) {
        User user = new User();
        user.setYhid(yhid);
        user.setYhkl(yhkl);
        boolean isLogin = userService.login(user);
        if (isLogin) {
            return "homePage";
        } else {
            return "login";
        }
    }


    @RequestMapping(value = "/displayUserInfo", produces = "application/xml;charset=utf-8")
    @ResponseBody
    public String displayUserInfo(@RequestParam("start") String start, @RequestParam("limit") String limit,
                                  @Nullable @RequestParam("yhzh") String yhid, @Nullable @RequestParam("yhbm") String yhbm) {
        if ("".equals(start)) {
            start = "0";
        }

        if ("".equals(limit)) {
            limit = "0";
        }

        int sta = Integer.parseInt(start);
        int lim = Integer.parseInt(limit);

        YhxxDto yhxxDto = new YhxxDto();
        yhxxDto.setStart(sta);
        yhxxDto.setLimit(lim);
        yhxxDto.setYhbm(yhbm);
        yhxxDto.setYhid(yhid);

        String xml = userService.userInfoDisplay(yhxxDto);
        System.out.println(xml);
        return xml;
    }

    @RequestMapping("/loadSelection")
    @ResponseBody
    public List<Depart> loadSelection() {
        return departService.queryDepart();
    }
}
