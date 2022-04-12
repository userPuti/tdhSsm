package com.tdh.controller;

import com.tdh.cache.Caches;
import com.tdh.domain.Bzdm;
import com.tdh.domain.Depart;
import com.tdh.domain.User;
import com.tdh.dto.YhxxDto;
import com.tdh.service.DepartService;
import com.tdh.service.UserService;
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

    @RequestMapping("/loadGenderSel")
    @ResponseBody
    public List<Bzdm> loadGenderSel() {
        List<Bzdm> bzdms = new ArrayList<>();
        if (!Caches.bzdmMap.isEmpty()) {
            for (Map.Entry<String, Bzdm> departEntry : Caches.bzdmMap.entrySet()) {
                bzdms.add(departEntry.getValue());
            }
        }
        return bzdms;
    }


    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(User user) {
        String isSucc = userService.insertUser(user);
        return "success";
    }


    @RequestMapping("/bulkDel")
    @ResponseBody
    public String bulkDel(@RequestParam("del") String ids) {
        boolean isSucc = userService.bulkDeletion(ids);
        if (isSucc) {
            return "1";
        } else {
            return "0";
        }
    }

    @RequestMapping("/viewUserInfo")
    public ModelAndView viewUserInfo(@RequestParam("yhid") String yhid, @RequestParam("func") String func) {
        ModelAndView mav = new ModelAndView();
        if (func.equals("view")) {
            mav.setViewName("userInfo");
        }
        if (func.equals("modify")) {
            mav.setViewName("modify");
        }
        User user = userService.selectUserById(yhid);
        mav.addObject("user", user);
        System.out.println(user);
        return mav;
    }


    @RequestMapping("/update")
    @ResponseBody
    public String updateInfo(User user) {
        boolean isSucc = userService.updateUserInfo(user);
        if (isSucc) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping("/user_form.do")
    public ModelAndView user_form(String yhid, String func) {
        ModelAndView mav = new ModelAndView("user_form");
        YhxxDto yhxxDto = new YhxxDto();
        yhxxDto.setYhid(yhid);
        yhxxDto.setYhbm(null);
        yhxxDto.setStart(0);
        yhxxDto.setLimit(1);
        User user = userService.viewUserInfo(yhxxDto);
        List<Depart> list = departService.queryDepart();
        StringBuffer yhbmSelect = new StringBuffer("<option></option>");
        for (Depart depart : list) {
            if (depart.getBmdm().equals(user.getYhbm())) {
                yhbmSelect.append("<option selected value='" + depart.getBmdm() + "'>").append(depart.getBmmc()).append("</option>");
            } else {
                yhbmSelect.append("<option value='" + depart.getBmdm() + "'>").append(depart.getBmmc()).append("</option>");
            }
        }
        mav.addObject("user", user);
        mav.addObject("yhbmSelect", yhbmSelect.toString());
        mav.addObject("func", func);
        return mav;
    }


}
