package com.tdh.service.impl;

import com.tdh.domain.User;
import com.tdh.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ImplUserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void selectAllUser() {
        User user = userService.selectUserById("196");
    }


}