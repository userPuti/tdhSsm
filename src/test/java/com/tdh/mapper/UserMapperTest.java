package com.tdh.mapper;

import com.tdh.domain.User;
import com.tdh.domain.UserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void selectUserByYhid(){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andYhidEqualTo("196");

        List<User> users = userMapper.selectByExampleWithBLOBs(userExample);

        System.out.println(users.get(0).getPhoto());
        System.out.println(users.get(0).getYhid());
    }


}