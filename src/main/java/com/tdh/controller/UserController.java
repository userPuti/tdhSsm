package com.tdh.controller;

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
     * 展示主页全部用户信息
     *
     * @param yhxxDto 用户信息的入参对象
     * @return grid需要加载的xml String
     */
    @RequestMapping(value = "/displayUserInfo", produces = "application/xml;charset=utf-8")
    @ResponseBody
    public String displayUserInfo(YhxxDto yhxxDto) {
        String xml = userService.userInfoDisplay(yhxxDto);
        if (!"".equals(xml)) {
            return xml;
        } else {
            return "";
        }
    }

    /**
     * 加载部门下拉框
     *
     * @return 部门对象集合
     */
    @RequestMapping("/loadDepartSel")
    @ResponseBody
    public List<Depart> loadSelection() {
        List<Depart> departs = new ArrayList<>();
        if (!Caches.departMap.isEmpty()) {
            for (Map.Entry<String, Depart> departEntry : Caches.departMap.entrySet()) {
                departs.add(departEntry.getValue());
            }
        }
        return departs;
    }

    /**
     * 加载性别下拉框
     *
     * @return 性别对象集合
     */
    @RequestMapping("/loadGenderSel")
    @ResponseBody
    public List<Bzdm> loadGenderSel() {
        List<Bzdm> bzdms = new ArrayList<>();
        if (!Caches.genderMap.isEmpty()) {
            for (Map.Entry<String, Bzdm> departEntry : Caches.genderMap.entrySet()) {
                bzdms.add(departEntry.getValue());
            }
        }
        return bzdms;
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
     *
     */
    @RequestMapping("/viewUserInfo")
    public ModelAndView viewUserInfo(String yhid, String func) {
        ModelAndView mav = new ModelAndView("userForm");
        mav.addObject("func", func);
        User user = userService.selectUserById(yhid);
        mav.addObject("user", user);
        return mav;
    }

    /**
     * 用于跳转到添加页面
     * @param func 功能参数，为添加add
     * @return 返回一个ModelAndView对象，同时传递func参数到userForm页面
     */
    @RequestMapping("/jumpToAddPage")
    public ModelAndView jumpToAddPage(String func){
        ModelAndView modelAndView = new ModelAndView("userForm");
        modelAndView.addObject("func",func);
        return modelAndView;
    }
}
