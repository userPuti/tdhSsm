package com.tdh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdh.cache.Caches;
import com.tdh.domain.Bzdm;
import com.tdh.domain.User;
import com.tdh.domain.UserExample;
import com.tdh.dto.YhxxDto;
import com.tdh.mapper.UserMapper;
import com.tdh.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author puti
 * @date 2022/3/18
 */
@Service("userService")
public class ImplUserService implements UserService {

    private static final Log log = LogFactory.getLog(ImplUserService.class);

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
        if (yhbm != null && !"".equals(yhbm)) {
            user.setYhbm(Caches.departMap.get(yhbm).getBmmc());
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
            log.debug("查询 " + yhid + "的信息");
            UserExample userExample = new UserExample();
            userExample.createCriteria().andYhidEqualTo(yhid);
            List<User> users = userMapper.selectByExampleWithBLOBs(userExample);
            if (users.size() > 0) {
                log.debug("查询到了 " + yhid + "的信息" + users.get(0));
                return transferToRealInfo(users.get(0));
            }
        }
        log.info("未找到 " + yhid + " 的信息");
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
            log.debug("查找 " + user.getYhid() + " 的登录信息");
            UserExample userExample = new UserExample();
            userExample.createCriteria().andYhidEqualTo(user.getYhid()).andYhklEqualTo(user.getYhkl());
            List<User> users = userMapper.selectByExample(userExample);
            log.debug(user.getYhid() + " 的登录信息： " + users.get(0));
            return users.size() > 0;
        }
        log.info(user.getYhid() + " 登录失败");
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
            log.debug("查询主页表格中的用户信息");
            PageHelper.offsetPage(yhxxDto.getStart() - 1, yhxxDto.getLimit());
            List<User> users = userMapper.selectUser(yhxxDto);
            PageInfo<User> usersPageInfo = new PageInfo<>(users);
            int total = (int) usersPageInfo.getTotal();
            log.debug("主页表格中的用户信息已查询完毕");
            return userListXml(users, total);
        }
        log.info("主页表格信息未找到或为空！");
        return "";
    }

    /**
     * 新增用户信息
     *
     * @param user user对象
     * @return xml格式
     */
    @Override
    @Transactional
    public boolean insertUser(User user, String photoPath) {
        if (user != null) {
            log.debug("插入用户" + user);
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
            log.debug(user.getYhid() + " 用户信息添加成功");
            return rows > 0;
        }
        log.info(user.getYhid() + " 用户信息添加失败");
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
    @Transactional
    public boolean deleteUserInfo(YhxxDto yhxxDto) {
        if (yhxxDto != null) {
            log.warn("正在删除用户信息");
            int isSucc = userMapper.deleteByPrimaryKey(yhxxDto.getYhid());
            return 1 == isSucc;
        }
        log.info("用户信息删除失败");
        return false;
    }

    /**
     * 更新用户信息
     *
     * @param user user对象
     * @return 是否插入成功
     */
    @Override
    @Transactional
    public boolean updateUserInfo(User user, String photoPath) {
        if (user != null) {
            log.debug(user.getYhid() + " 正在更新用户信息为 " + user);
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
            log.debug(user.getYhid() + " 的用户信息更新成功");
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
            log.debug("查看 " + yhxxDto.getYhid() + " 的信息");
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
                log.info("查看用户信息失败");
                return null;
            }
        }
        log.info("没有找到用户信息");
        return null;
    }

    /**
     * 批量删除用户信息
     *
     * @param dels 用户的信息集合
     * @return 是否删除成功
     */
    @Override
    @Transactional
    public int batchDelete(String dels) {
        if (dels != null && !"".equals(dels)) {
            log.warn("正在删除用户信息");
            String[] delYhids = dels.trim().split(",");
            return userMapper.batchdelete(delYhids);
        }
        log.info("删除用户信息失败");
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
        log.debug("正在查询用户" + yhid + "的头像信息");
        return userMapper.selectPhotonameByYhid(yhid);
    }


    /**
     * 将图片从数据库下载到服务器，并且返回图片新的名字
     *
     * @param yhid 用户id
     * @return 图片名称
     */
    @Override
    @Transactional
    public String getPhotoName(String yhid, String photoPath) {
        log.debug("正在查询用户" + yhid + "的头像信息");
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
            log.error("用户头像获取出现错误", e);
        }

        return photoname;
    }
}
