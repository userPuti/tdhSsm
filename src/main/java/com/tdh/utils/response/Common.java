package com.tdh.utils.response;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Puti
 * @date 2022/4/20 9:56
 */
public class Common {

    /**
     * @param localfile 项目下的相对地址  (/temp/aa.png)
     * @param request
     * @return
     */
    public String buildHttplj(String localfile, HttpServletRequest request) {
        String httpurl = "";
        String basePath = "";
        if (request != null) {
            basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
        }
        //basePath = http://ip:port/ssmPrac/temp/aa.png
        //<img src=''/>
        if (!"".equals(localfile)) {
            localfile = localfile.replace("\\", "/");
            httpurl = basePath + localfile;
            httpurl = httpurl.replace("\\", "/");
        }
        return httpurl;
    }
}
