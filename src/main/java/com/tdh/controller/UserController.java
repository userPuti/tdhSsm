package com.tdh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdh.cache.Caches;
import com.tdh.domain.Bzdm;
import com.tdh.domain.Depart;
import com.tdh.domain.User;
import com.tdh.dto.YhxxDto;
import com.tdh.service.UserService;
import com.tdh.utils.response.ResResult;
import com.tdh.utils.response.ResponseVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * @author Puti
 * @date 2022/4/9 10:52
 */
@Controller
public class UserController {

    private static Map<String, String> idPhotoNameMap = new HashMap<>();
    private static final Log log = LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;


    /**
     * 加载用户信息
     *
     * @param yhxxDto 用户信息入参对象
     * @return 用户信息的xml格式字符串
     */
    @RequestMapping(value = "/loadUserXml", produces = "application/xml;charset=utf-8")
    @ResponseBody
    public String loadUserXml(YhxxDto yhxxDto) {
        System.out.println("============================================="+System.getProperty("ssmPrac.root"));
        log.debug("loadUserXml 接口入参： " + yhxxDto);
        String userInfo = userService.userInfoDisplay(yhxxDto);
        log.debug("加载了页面数据!");
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
    public ResponseVO addUser(User user, HttpSession httpsession) {
        String username = (String) httpsession.getAttribute("username");
        log.debug("管理员" + username + "添加用户的信息是：" + user);
        String photoPath = httpsession.getServletContext().getRealPath("photo");
        httpsession.getAttribute("user");
        boolean bInsert = userService.insertUser(user, photoPath);
        if (bInsert) {
            log.info("管理员" + username + "添加了一个用户");
            return ResResult.success();
        } else {
            log.info("管理员" + username + "添加用户失败");
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
    public ResponseVO bulkDel(@RequestParam("del") String ids,HttpSession httpSession) {
        String username = (String) httpSession.getAttribute("username");
        log.warn("管理员" + username + "批量删除的用户id" + ids);
        String[] idArray = ids.trim().split(",");
        int total = idArray.length;
        int succCount = userService.batchDelete(ids);
        int fail = total - succCount;
        if (succCount > 0) {
            log.info("管理员" + username + "删除了" + succCount + "条数据");
            return ResResult.successWithData("成功删除了" + succCount + "条数据,失败了" + fail + "条");
        } else {
            log.debug("管理员" + username + "删除失败");
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
    public ResponseVO updateInfo(User user, HttpSession httpSession) {
        String username = (String) httpSession.getAttribute("username");
        log.debug("管理员" + username + "更新用户的信息" + user);
        String yhid = user.getYhid();

        String photoPath = httpSession.getServletContext().getRealPath("photo");

        boolean isSucc = userService.updateUserInfo(user, photoPath);
        if (isSucc) {
            log.info("管理员" + username + "用户信息更新成功");
            return ResResult.success();
        } else {
            log.info("管理员" + username + "用户信息更新失败");
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
    public ModelAndView viewUserInfo(HttpSession httpSession,String yhid, String func, @Nullable String kind, HttpServletRequest request) {
        String username = (String) httpSession.getAttribute("username");
        log.debug("管理员" + username + "准备查看了用户" + yhid + "的信息");
        ModelAndView modelAndView = new ModelAndView("userForm");
        modelAndView.addObject("func", func);
        User user = userService.selectUserById(yhid);
        modelAndView.addObject("user", user);

        if (user.getPhoto() != null) {
            ServletContext servletContext = request.getSession().getServletContext();
            String photoPath = servletContext.getRealPath("photo");
            String photoName = userService.getPhotoName(yhid, photoPath);
            String imgSrc = "photo/" + photoName;
            modelAndView.addObject("imgSrc", imgSrc);
        }

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
            log.error("管理员" + username + "部门或性别的下拉框的json对象转换失败", e);
        }
        log.debug("查看了用户" + yhid + "的信息");
        return modelAndView;
    }

    /**
     * 用于跳转到添加页面
     *
     * @param func 功能参数，为添加add
     * @return 返回一个ModelAndView对象，同时传递func参数到userForm页面
     */
    @RequestMapping("/jumpToAddPage")
    public ModelAndView jumpToAddPage(HttpSession httpSession,String func, String kind) {
        String username = (String) httpSession.getAttribute("username");
        log.debug("管理员" + username + "准备添加用户");
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
            log.error("部门或性别的下拉框的json对象转换失败", e);
        }
        modelAndView.addObject("func", func);
        log.debug("跳转到添加用户页面");
        return modelAndView;
    }


    @RequestMapping("/upload")
    @ResponseBody
    public ResponseVO uploadPicture(HttpSession httpSession,MultipartFile photo, @RequestParam("yhid") String yhid, HttpSession session) {
        String username = (String) httpSession.getAttribute("username");
        log.debug("管理员" + username + "准备上传用户" + yhid + "的头像");
        //获取上传的文件的文件名
        String fileName = photo.getOriginalFilename();
        //处理文件重名问题
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString() + hzName;
        //获取服务器中photo目录的路径
        ServletContext servletContext = session.getServletContext();
        String photoPath = servletContext.getRealPath("photo");
        File file = new File(photoPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String finalPath = photoPath + File.separator + fileName;

        //实现上传功能
        try {
            photo.transferTo(new File(finalPath));
        } catch (IOException e) {
            log.error("上传失败", e);
        }
        idPhotoNameMap.put(yhid, fileName);
        log.debug("管理员" + username + "上传了用户" + yhid + "的头像");
        return ResResult.successWithData("photo/" + fileName);
    }


    @RequestMapping("/download")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession httpSession, @RequestParam("yhid") String yhid) {
        String username = (String) httpSession.getAttribute("username");
        log.debug("管理员" + username + "准备下载用户" + yhid + "的头像");
        ResponseEntity<byte[]> responseEntity = null;
        InputStream is = null;
        try {
            String photoname = userService.selectPhotonameById(yhid);
            //获取ServletContext对象
            ServletContext servletContext = httpSession.getServletContext();
            String photoPath = servletContext.getRealPath("photo");
            String photoName = userService.getPhotoName(yhid, photoPath);
            //获取服务器中文件的真实路径
            String realPath = photoPath + File.separator + photoName;

            //创建输入流
            is = new FileInputStream(realPath);

            //创建字节数组
            byte[] bytes = new byte[is.available()];
            //将流读到字节数组中
            is.read(bytes);
            //创建HttpHeaders对象设置响应头信息
            MultiValueMap<String, String> headers = new HttpHeaders();
            //设置要下载方式以及下载文件的名字
            headers.add("Content-Disposition", "attachment;filename=" + photoname);
            //设置响应状态码
            HttpStatus statusCode = HttpStatus.OK;
            //创建ResponseEntity对象
            responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        } catch (FileNotFoundException e) {
            log.error("下载过程中查找不到文件", e);
        } catch (IOException e) {
            log.error("下载过程中IO异常", e);
        } finally {
            //关闭输入流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("关闭输入流出现异常", e);
                }
            }
        }
        log.debug("下载完成");
        return responseEntity;
    }
}
