package com.tdh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdh.cache.Caches;
import com.tdh.domain.Bzdm;
import com.tdh.domain.Depart;
import com.tdh.domain.User;
import com.tdh.domain.UserExample;
import com.tdh.dto.YhxxDto;
import com.tdh.mapper.UserMapper;
import com.tdh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author puti
 * @date 2022/3/18
 */
@Service("userService")
public class ImplUserService implements UserService {

    @Autowired
    private UserMapper userMapper;


    private String userListXml(List<User> list, int count) {
        StringBuilder allUserxml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        if (list != null && count > 0) {
            allUserxml.append("<rows>");
            allUserxml.append("<userdata name='totalnumber'><![CDATA[").append(count).append("]]></userdata>");
            for (User user : list) {
                transferToRealInfo(user);

                allUserxml.append("<row id=\"").append(user.getYhid()).append("\">");
                allUserxml.append("<cell>0</cell>");
                allUserxml.append("<cell><![CDATA[").append(user.getYhid()).append("]]></cell>");
                allUserxml.append("<cell><![CDATA[").append(user.getYhxm()).append("]]></cell>");
                allUserxml.append("<cell><![CDATA[").append(user.getYhxb()).append("]]></cell>");
                allUserxml.append("<cell><![CDATA[").append(user.getYhbm()).append("]]></cell>");

                String csrq = user.getCsrq();
                if ("".equals(csrq)) {
                    allUserxml.append("<cell><![CDATA[").append("-").append("]]></cell>");
                } else {
                    allUserxml.append("<cell><![CDATA[").append(csrq).append("]]></cell>");
                }
                allUserxml.append("<cell><![CDATA[").append(user.getDjrq()).append("]]></cell>");
                allUserxml.append("<cell><![CDATA[").append(user.getSfjy()).append("]]></cell>");

                Integer pxh = user.getPxh();
                if (null == pxh) {
                    allUserxml.append("<cell><![CDATA[").append("-").append("]]></cell>");
                } else {
                    allUserxml.append("<cell><![CDATA[").append(pxh).append("]]></cell>");
                }

                allUserxml.append("<cell><![CDATA[").append("/ssm/static/images/search.png^查看^javascript:view(\"").append(user.getYhid()).append("\")^_self").append("]]></cell>");
                allUserxml.append("<cell><![CDATA[").append("/ssm/static/images/modify.png^修改^javascript:modify(\"").append(user.getYhid()).append("\")^_self").append("]]></cell>");
                allUserxml.append("<cell><![CDATA[").append("/ssm/static/images/delete.png^删除^javascript:delInfo(\"").append(user.getYhid()).append("\")^_self").append("]]></cell>");
                allUserxml.append("</row>");
            }
            allUserxml.append("</rows>");
        } else {
            //没数据
            allUserxml.append("<rows><userdata name='totalnumber'>0</userdata></rows>");
        }
        return allUserxml.toString();
    }

    private User transferToRealInfo(User user) {

        String yhbm = user.getYhbm();
//        Caches.departMap.get(yhbm).getBmmc();
        if (yhbm != null && !"".equals(yhbm)) {
            for (Map.Entry<String, Depart> departEntry : Caches.departMap.entrySet()) {
                if (departEntry.getKey().equals(yhbm)) {
                    user.setYhbm(departEntry.getValue().getBmmc());
                }
            }
        } else {
            user.setYhbm("-");
        }

        String yhxb = user.getYhxb();
        List<Bzdm> gender = Caches.bzdm_kind_Map.get("00003");
        if (yhxb != null && !"".equals(yhxb)) {
            for (Bzdm gen : gender) {
                if (Objects.equals(yhxb, gen.getCode())) {
                    user.setYhxb(gen.getMc());
                }
            }
        } else {
            user.setYhxb("-");
        }

        String sfjy = user.getSfjy();

        if (Objects.equals(sfjy, "1")) {
            user.setSfjy("是");
        } else if (Objects.equals(sfjy, "0")) {
            user.setSfjy("否");
        }

        String csrq = user.getCsrq();
        if (csrq == null || "".equals(csrq)) {
            user.setCsrq("-");
        }

        return user;
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param yhid 用户id
     * @return 如果查询到相应的对象，则返回该用户的对象信息，否则返回null
     */
    @Override
    public User selectUserById(String yhid) {
        if (!Objects.equals(yhid, "")) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andYhidEqualTo(yhid);
            List<User> users = userMapper.selectByExampleWithBLOBs(userExample);
            if (users.size() > 0) {
                return transferToRealInfo(users.get(0));
            }
        }
        return null;
    }


    /**
     * 登录
     *
     * @param user 传入User对象
     * @return 返回User对象, 没有则返回null
     */
    @Override
    public boolean login(User user) {
        if (user != null) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andYhidEqualTo(user.getYhid()).andYhklEqualTo(user.getYhkl());
            List<User> users = userMapper.selectByExample(userExample);
            return users.size() > 0;
        }
        return false;
    }

    /**
     * 展示用户信息
     *
     * @param yhxxDto 用户信息入参对象
     * @return xml格式
     */
    @Override
    public String userInfoDisplay(YhxxDto yhxxDto) {
        if (yhxxDto != null) {
            PageHelper.offsetPage(yhxxDto.getStart() - 1, yhxxDto.getLimit());
            List<User> users = userMapper.selectUser(yhxxDto);
            PageInfo<User> usersPageInfo = new PageInfo<>(users);
            int total = (int) usersPageInfo.getTotal();
            return userListXml(users, total);
        }

        return "";
    }

    /**
     * 新增用户信息
     *
     * @param user user对象
     * @return xml格式
     */
    @Override
//    @Transactional("")
    public boolean insertUser(User user, String  photoPath) {
        if (user != null) {
            String sfjy = user.getSfjy();

            if (Objects.equals(sfjy, "on")) {
                user.setSfjy("1");
            } else {
                user.setSfjy("0");
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String djrq = df.format(new Date());
            user.setDjrq(djrq);

            String photoname = user.getPhotoname();
            if (photoname != null && !"".equals(photoname)) {
                String phototype = photoname.substring(photoname.lastIndexOf(".") + 1);
                user.setPhototype(phototype);

                String finalPath = photoPath + File.separator + photoname;

                byte[] imageBinary = getImageBinary(finalPath, phototype);
                user.setPhoto(imageBinary);
            }
            int rows = userMapper.insertSelective(user);

            return rows > 0;
        }

        return false;
    }


    private byte[] getImageBinary(String path, String phototype) {
        File f = new File(path);
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, phototype, baos);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 删除用户信息
     *
     * @param yhxxDto 用户信息的入参对象
     * @return 是否删除成功
     */
    @Override
    public boolean deleteUserInfo(YhxxDto yhxxDto) {
        if (yhxxDto != null) {
            int isSucc = userMapper.deleteByPrimaryKey(yhxxDto.getYhid());
            return 1 == isSucc;
        }
        return false;
    }

    /**
     * 更新用户信息
     *
     * @param user user对象
     * @return 是否插入成功
     */
    @Override
    public boolean updateUserInfo(User user, String photoPath) {
        if (user != null) {
            String sfjy = user.getSfjy();
            if (Objects.equals(sfjy, "on")) {
                user.setSfjy("1");
            } else {
                user.setSfjy("0");
            }

            String photoname = user.getPhotoname();
            if (photoname != null && !"".equals(photoname)) {
                String phototype = photoname.substring(photoname.lastIndexOf(".") + 1);
                user.setPhototype(phototype);

                String finalPath = photoPath + File.separator + photoname;

                byte[] imageBinary = getImageBinary(finalPath, phototype);
                user.setPhoto(imageBinary);
            }

            int isSucc = userMapper.updateByPrimaryKeySelective(user);
            return 1 == isSucc;
        }
        return false;
    }

    /**
     * 查看用户的详细信息
     *
     * @param yhxxDto 用户信息的入参对象
     * @return 返回查询到的user对象，否则为null
     */
    @Override
    public User viewUserInfo(YhxxDto yhxxDto) {
        if (yhxxDto != null) {
            User user = userMapper.selectByPrimaryKey(yhxxDto.getYhid());

            if (user != null) {
                if ("".equals(user.getYhxb()) || null == user.getYhxb()) {
                    user.setYhxb("-");
                }
                if ("".equals(user.getCsrq()) || null == user.getCsrq()) {
                    user.setCsrq("-");
                }
                if ("".equals(user.getYhbm()) || null == user.getYhbm()) {
                    user.setYhbm("-");
                }
                if (null == user.getPxh()) {
                    user.setPxh(0);
                }

                return transferToRealInfo(user);
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 批量删除用户信息
     *
     * @param dels 用户的信息集合
     * @return 是否删除成功
     */
    @Override
    public int batchDelete(String dels) {
        if (dels != null && !"".equals(dels)) {
            String[] delYhids = dels.trim().split(",");
            return userMapper.batchdelete(delYhids);
        }
        return 0;
    }


    /**
     * 根据用户id查询图片名称
     *
     * @param yhid 用户id
     * @return 返回头像的图片名称
     */
    @Override
    public String selectPhotonameById(String yhid) {
        return userMapper.selectPhotonameByYhid(yhid);
    }


    /**
     * 将图片从数据库下载到服务器，并且返回图片新的名字
     *
     * @param yhid 用户id
     * @return 图片名称
     */
    @Override
    public String getPhotoName(String yhid, String photoPath) {
        User user = userMapper.selectByPrimaryKey(yhid);
        String photoname = null;

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(user.getPhoto());
            BufferedImage bi1 = ImageIO.read(bais);
            photoname = UUID.randomUUID().toString() + "." + user.getPhototype();

            File file = new File(photoPath);
            if (!file.exists()) {
                file.mkdir();
            }

            String finalPath = photoPath + File.separator + photoname;

            File w2 = new File(finalPath);
            ImageIO.write(bi1, user.getPhototype(), w2);

            userMapper.updatePhotonameByYhid(yhid, photoname);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return photoname;
    }
}
