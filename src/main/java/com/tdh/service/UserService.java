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
    public boolean insertUser(User user);


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
    public int bulkDeletion(String del);

    /**
     * 根据用户id查询用户信息
     * @param yhid 用户id
     * @return 如果查询到相应的对象，则返回该用户的对象信息，否则返回null
     */
    public User selectUserById(String yhid);
}
