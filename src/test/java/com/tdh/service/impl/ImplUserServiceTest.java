package com.tdh.service.impl;

import com.tdh.domain.User;
import com.tdh.service.UserService;
import org.junit.Test;

import java.util.List;

public class ImplUserServiceTest {

    @Test
    public void selectAllUser() {
        UserService userService = new ImplUserService();
        List<User> users = userService.selectAllUser();
        System.out.println(users);
    }
}