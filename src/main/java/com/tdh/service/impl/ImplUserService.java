package com.tdh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdh.domain.User;
import com.tdh.domain.UserExample;
import com.tdh.dto.YhxxDto;
import com.tdh.mapper.UserMapper;
import com.tdh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
            allUserxml.append("<userdata name='totalnumber'>").append(count).append("</userdata>");
            for (User user : list) {
                transferToRealInfo(user);

                allUserxml.append("<row id=\"" + user.getYhid() + "\">");
                allUserxml.append("<cell>0</cell>");
                allUserxml.append("<cell>").append(user.getYhid()).append("</cell>");
                allUserxml.append("<cell>").append(user.getYhxm()).append("</cell>");
                allUserxml.append("<cell>").append(user.getYhxb()).append("</cell>");
                allUserxml.append("<cell>").append(user.getYhbm()).append("</cell>");

                String csrq = user.getCsrq();
                if ("".equals(csrq)) {
                    allUserxml.append("<cell>").append("-").append("</cell>");
                } else {
                    allUserxml.append("<cell>").append(csrq).append("</cell>");
                }
                allUserxml.append("<cell>").append(user.getDjrq()).append("</cell>");
                allUserxml.append("<cell>").append(user.getSfjy()).append("</cell>");


                Integer pxh = user.getPxh();
                if (null == pxh) {
                    allUserxml.append("<cell>").append("-").append("</cell>");
                } else {
                    allUserxml.append("<cell>").append(pxh).append("</cell>");
                }

                allUserxml.append("<cell>").append("/ssm/static/images/search.png^查看^javascript:view(\"").append(user.getYhid()).append("\")^_self").append("</cell>");
                allUserxml.append("<cell>").append("/ssm/static/images/modify.png^修改^javascript:modify(\"").append(user.getYhid()).append("\")^_self").append("</cell>");
                allUserxml.append("<cell>").append("/ssm/static/images/delete.png^删除^javascript:delInfo(\"").append(user.getYhid()).append("\")^_self").append("</cell>");
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

        if (!"".equals(yhbm)) {
            if (Objects.equals(yhbm, "32010001")) {
                user.setYhbm("立案庭");
            } else if (Objects.equals(yhbm, "32010002")) {
                user.setYhbm("业务庭");
            } else if (Objects.equals(yhbm, "32010003")) {
                user.setYhbm("办公室");
            }
        } else {
            user.setYhbm("-");
        }

        String yhxb = user.getYhxb();

        if (!"".equals(yhxb)) {
            if (Objects.equals(yhxb, "09_00003-1")) {
                user.setYhxb("男");
            } else if (Objects.equals(yhxb, "09_00003-2")) {
                user.setYhxb("女");
            } else if (Objects.equals(yhxb, "09_00003-255")) {
                user.setYhxb("其他");
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
        if (csrq == null || csrq.equals("")) {
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
            List<User> users = userMapper.selectByExample(userExample);
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
    public boolean insertUser(User user) {
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

            int rows = userMapper.insertSelective(user);

            return rows > 0;
        }

        return false;
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
     * 查看用户的详细信息，用于弹窗
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
