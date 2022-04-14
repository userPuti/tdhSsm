package com.tdh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdh.cache.Caches;
import com.tdh.domain.Bzdm;
import com.tdh.domain.Depart;
import com.tdh.domain.User;
import com.tdh.dto.YhxxDto;
import com.tdh.service.DepartService;
import com.tdh.service.UserService;
import com.tdh.utils.response.ResResult;
import com.tdh.utils.response.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Puti
 * @date 2022/4/9 10:52
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartService departService;

    /**
     * 加载用户信息
     * @param yhxxDto 用户信息入参对象
     * @return 用户信息的xml格式字符串
     */
    @RequestMapping(value = "/loadUserXml",produces = "application/xml;charset=utf-8")
    @ResponseBody
    public String loadUserXml(YhxxDto yhxxDto) {
        String userInfo = userService.userInfoDisplay(yhxxDto);
        return userInfo;
    }

    /**
     * 添加一个用户信息
     *
     * @param user 前端传来的user对象
     * @return ResponseVO对象
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public ResponseVO addUser(User user) {
        boolean bInsert = userService.insertUser(user);
        if (bInsert) {
            return ResResult.success();
        } else {
            return ResResult.fail();
        }
    }


    /**
     * 批量删除
     *
     * @param ids 选中要删除的用户id
     * @return ResponseVO对象，回传成功的条数，和失败的条数
     */
    @RequestMapping(value = "/bulkDel", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseVO bulkDel(@RequestParam("del") String ids) {
        String[] idArray = ids.trim().split(",");
        int total = idArray.length;
        int succCount = userService.bulkDeletion(ids);
        int fail = total - succCount;
        if (succCount > 0) {
            return ResResult.successWithData("成功删除了" + succCount + "条数据,失败了" + fail + "条");
        } else {
            return ResResult.fail();
        }
    }

    /**
     * 更新用户信息
     *
     * @param user 新的user对象数据
     * @return ResponseVO对象
     */
    @RequestMapping(value = "/update", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseVO updateInfo(User user) {
        boolean isSucc = userService.updateUserInfo(user);
        if (isSucc) {
            return ResResult.success();
        } else {
            return ResResult.fail();
        }
    }

    /**
     * 查看用户信息
     *
     * @param yhid 用户id信息
     * @param func 功能参数，分为两个，一个是查看功能，一个是修改功能
     * @return 返回一个mav对象，包含一个功能参数和user对象，回传到userForm页面，
     */
    @RequestMapping("/viewUserInfo")
    public ModelAndView viewUserInfo(String yhid, String func, @Nullable String kind) {
        ModelAndView modelAndView = new ModelAndView("userForm");
        modelAndView.addObject("func", func);
        User user = userService.selectUserById(yhid);
        modelAndView.addObject("user", user);


        List<Depart> departs = new ArrayList<>();
        if (!Caches.departMap.isEmpty()) {
            for (Map.Entry<String, Depart> departEntry : Caches.departMap.entrySet()) {
                departs.add(departEntry.getValue());
            }
        }

        List<Bzdm> gender = Caches.bzdm_kind_Map.get(kind);
        try {
            modelAndView.addObject("departs", new ObjectMapper().writeValueAsString(departs).replaceAll("\"", "&quot;"));
            modelAndView.addObject("gender", new ObjectMapper().writeValueAsString(gender).replaceAll("\"", "&quot;"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    /**
     * 用于跳转到添加页面
     *
     * @param func 功能参数，为添加add
     * @return 返回一个ModelAndView对象，同时传递func参数到userForm页面
     */
    @RequestMapping("/jumpToAddPage")
    public ModelAndView jumpToAddPage(String func, String kind) {
        ModelAndView modelAndView = new ModelAndView("userForm");
        List<Depart> departs = new ArrayList<>();
        if (!Caches.departMap.isEmpty()) {
            for (Map.Entry<String, Depart> departEntry : Caches.departMap.entrySet()) {
                departs.add(departEntry.getValue());
            }
        }
        List<Bzdm> gender = Caches.bzdm_kind_Map.get(kind);
        try {
            modelAndView.addObject("departs", new ObjectMapper().writeValueAsString(departs).replaceAll("\"", "&quot;"));
            modelAndView.addObject("gender", new ObjectMapper().writeValueAsString(gender).replaceAll("\"", "&quot;"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("func", func);
        return modelAndView;
    }
}
