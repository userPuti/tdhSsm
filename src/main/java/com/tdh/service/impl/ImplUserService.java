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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

                allUserxml.append("<cell><![CDATA[").append("/ssm/static/images/search.png^??????^javascript:view(\"").append(user.getYhid()).append("\")^_self").append("]]></cell>");
                allUserxml.append("<cell><![CDATA[").append("/ssm/static/images/modify.png^??????^javascript:modify(\"").append(user.getYhid()).append("\")^_self").append("]]></cell>");
                allUserxml.append("<cell><![CDATA[").append("/ssm/static/images/delete.png^??????^javascript:delInfo(\"").append(user.getYhid()).append("\")^_self").append("]]></cell>");
                allUserxml.append("</row>");
            }
            allUserxml.append("</rows>");
        } else {
            //?????????
            allUserxml.append("<rows><userdata name='totalnumber'>0</userdata></rows>");
        }
        return allUserxml.toString();
    }

    private User transferToRealInfo(User user) {
        String yhbm = user.getYhbm();
        if (yhbm != null && !yhbm.equals("")) {
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
        if (yhxb != null && !yhxb.equals("")) {
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
            user.setSfjy("???");
        } else if (Objects.equals(sfjy, "0")) {
            user.setSfjy("???");
        }

        String csrq = user.getCsrq();
        if (csrq == null || csrq.equals("")) {
            user.setCsrq("-");
        }

        return user;
    }

    /**
     * ????????????id??????????????????
     *
     * @param yhid ??????id
     * @return ?????????????????????????????????????????????????????????????????????????????????null
     */
    @Override
    public User selectUserById(String yhid) {
        if (!Objects.equals(yhid, "")) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andYhidEqualTo(yhid);
            List<User> users = userMapper.selectByExample(userExample);
            if (users.size() > 0) {
                return transferToRealInfo(users.get(0));
            }
        }
        return null;
    }


    /**
     * ??????
     *
     * @param user ??????User??????
     * @return ??????User??????, ???????????????null
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
     * ??????????????????
     *
     * @param yhxxDto ????????????????????????
     * @return xml??????
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
     * ??????????????????
     *
     * @param user user??????
     * @return xml??????
     */
    @Override
    public boolean insertUser(User user) {
        if (user != null) {
            try {
                String yhid = URLDecoder.decode(URLDecoder.decode(user.getYhid(), "utf-8"),"utf-8");
                String yhxm = URLDecoder.decode(URLDecoder.decode(user.getYhxm(), "utf-8"),"utf-8");
                user.setYhid(yhid);
                user.setYhxm(yhxm);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String sfjy = user.getSfjy();

            if (Objects.equals(sfjy, "on")) {
                user.setSfjy("1");
            } else {
                user.setSfjy("0");
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String djrq = df.format(new Date());
            user.setDjrq(djrq);

            int rows = userMapper.insertSelective(user);

            return rows > 0;
        }

        return false;
    }

    /**
     * ??????????????????
     *
     * @param yhxxDto ???????????????????????????
     * @return ??????????????????
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
     * ??????????????????
     *
     * @param user user??????
     * @return ??????????????????
     */
    @Override
    public boolean updateUserInfo(User user) {
        if (user != null) {
            String sfjy = user.getSfjy();
            if (Objects.equals(sfjy, "on")) {
                user.setSfjy("1");
            } else {
                user.setSfjy("0");
            }

            int isSucc = userMapper.updateByPrimaryKeySelective(user);
            return 1 == isSucc;
        }
        return false;
    }

    /**
     * ??????????????????????????????????????????
     *
     * @param yhxxDto ???????????????????????????
     * @return ??????????????????user??????????????????null
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
     * ????????????????????????
     *
     * @param dels ?????????????????????
     * @return ??????????????????
     */
    @Override
    public int bulkDeletion(String dels) {
        if (dels != null && !dels.equals("")) {
            String[] delYhids = dels.trim().split(",");
            UserExample userExample = new UserExample();
            int count = 0;
            for (String yhid : delYhids) {
                UserExample.Criteria criteria = userExample.createCriteria().andYhidEqualTo(yhid);
                userExample.or(criteria);
                int isSucc = userMapper.deleteByExample(userExample);
                if (1 == isSucc) {
                    count++;
                } else {
                    return count;
                }
            }
            return count;
        }
        return 0;
    }

}
