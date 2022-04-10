package com.tdh.service;

import com.tdh.domain.User;
import com.tdh.dto.YhxxDto;

/**
 * @author puti
 * @date 2022/3/18
 */
public interface UserService {
    /**
     * 登录
     *
     * @param user 传入User对象
     * @return 返回User对象
     */
    public boolean login(User user);

    /**
     * 展示用户信息
     *
     * @param yhxxDto 用户信息入参对象
     * @return xml格式
     */
    public String userInfoDisplay(YhxxDto yhxxDto);

    /**
     * 新增用户信息
     *
     * @param user user对象
     * @return xml格式
     */
    public String insertUser(User user);


    /**
     * 删除用户信息
     *
     * @param yhxxDto 用户信息的入参对象
     * @return 是否删除成功
     */
    public boolean deleteUserInfo(YhxxDto yhxxDto);


    /**
     * 更新用户信息
     *
     * @param user user对象
     * @return 是否插入成功
     */
    public boolean updateUserInfo(User user);

    /**
     * 查看用户的详细信息，用于弹窗
     *
     * @param yhxxDto 用户信息的入参对象
     * @return 返回查询到的user对象，否则为null
     */
    public User viewUserInfo(YhxxDto yhxxDto);


    /**
     * 批量删除用户信息
     *
     * @param del 用户的信息集合
     * @return 是否删除成功
     */
    public boolean bulkDeletion(String del);


}
