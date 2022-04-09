package com.tdh.service.impl;

import com.tdh.domain.User;
import com.tdh.domain.UserExample;
import com.tdh.mapper.UserMapper;
import com.tdh.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Puti
 * @date 2022/4/9 10:44
 */
@Service("userService")
public class ImplUserService implements UserService {
    @Override
    public List<User> selectAllUser() {
        List<User> users = null;
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sessionFactory.openSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            UserExample userExample = new UserExample();
            userExample.createCriteria();
            users = mapper.selectByExample(userExample);
            sqlSession.commit();
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
}
