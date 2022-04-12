package com.tdh.controller;

import com.tdh.domain.User;
import com.tdh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Puti
 * @date 2022/4/11 9:38
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletResponse resp, @RequestParam("zh") String yhid, @RequestParam("kl") String yhkl,
                        @Nullable @RequestParam("jzzh") String jzzh, @Nullable @RequestParam("jzmm") String jzmm) {
        User user = new User();
        user.setYhid(yhid);
        user.setYhkl(yhkl);

        boolean isLogin = userService.login(user);

        if (isLogin) {
            if (jzzh != null) {
                Cookie zhanghao = new Cookie("zh", yhid);
                zhanghao.setMaxAge(60 * 60 * 24 * 7);
                resp.addCookie(zhanghao);
            }
            if (jzmm != null) {
                Cookie kouling = new Cookie("kl", yhkl);
                kouling.setMaxAge(60 * 60 * 24 * 7);
                resp.addCookie(kouling);
            }
            return "homePage";
        }

        return "login";
    }

}
