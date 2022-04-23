package com.tdh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdh.cache.Caches;
import com.tdh.domain.Bzdm;
import com.tdh.domain.Depart;
import com.tdh.domain.User;
import com.tdh.dto.YhxxDto;
import com.tdh.service.UserService;
import com.tdh.utils.response.Common;
import com.tdh.utils.response.ResResult;
import com.tdh.utils.response.ResponseVO;
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

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

/**
 * @author Puti
 * @date 2022/4/9 10:52
 */
@Controller
public class UserController {

    private static Map<String, String> idPhotoNameMap = new HashMap<>();

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
    public ResponseVO addUser(User user,HttpSession httpsession) {
        boolean bInsert = userService.insertUser(user,httpsession);
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
    public ResponseVO updateInfo(User user,HttpSession httpSession) {
        String yhid = user.getYhid();

        boolean isSucc = userService.updateUserInfo(user,httpSession);
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
    public ModelAndView viewUserInfo(String yhid, String func, @Nullable String kind, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("userForm");
        modelAndView.addObject("func", func);
        User user = userService.selectUserById(yhid);
        modelAndView.addObject("user", user);


        if (user.getPhoto() != null) {
            ServletContext servletContext = request.getSession().getServletContext();
            String photoPath = servletContext.getRealPath("photo");
            String finalPath = photoPath + File.separator + user.getPhotoname();

            Common common = new Common();
            String imgSrc = common.buildHttplj("/photo/" + user.getPhotoname() , request);

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


    @RequestMapping("/upload")
    @ResponseBody
    public ResponseVO uploadPicture(MultipartFile photo, @RequestParam("yhid") String yhid, HttpSession session) {
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
            e.printStackTrace();
        }
        idPhotoNameMap.put(yhid, fileName);
        return ResResult.successWithData("photo/" + fileName);
    }


    @RequestMapping("/download")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session, @RequestParam("yhid") String yhid) {
        String photoname = userService.selectPhotonameById(yhid);
        ResponseEntity<byte[]> responseEntity = null;
        InputStream is = null;
        try {
            //获取ServletContext对象
            ServletContext servletContext = session.getServletContext();
            //获取服务器中文件的真实路径
            String realPath = servletContext.getRealPath("/photo/" + photoname);
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
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭输入流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseEntity;
    }


    private void byte2image(byte[] data, String path) {
        if (data.length < 3 || path.equals(""))
            return;
        try {
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }


}
