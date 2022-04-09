package com.tdh.service.impl;

import com.tdh.domain.User;
import com.tdh.domain.UserExample;
import com.tdh.mapper.UserMapper;
import com.tdh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Puti
 * @date 2022/4/9 10:44
 */
@Service("userService")
public class ImplUserService implements UserService {

    @Autowired
    private UserMapper mapper;


    @Override
    public List<User> selectAllUser() {
        List<User> users = null;
        UserExample userExample = new UserExample();
        userExample.createCriteria();
        users = mapper.selectByExample(userExample);

        return users;
    }
}
