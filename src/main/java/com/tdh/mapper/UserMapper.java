package com.tdh.mapper;

import com.tdh.domain.User;
import com.tdh.domain.UserExample;
import java.util.List;

import com.tdh.dto.YhxxDto;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String yhid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExampleWithBLOBs(UserExample example);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String yhid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExampleWithBLOBs(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据用户的入参信息查询用户
     *
     * @param yhxxDto 用户的入参信息
     * @return 查询到的所有user对象
     */
    List<User> selectUser(YhxxDto yhxxDto);

    /**
     * 根据用户id查询头像名称
     * @param Yhid 用户的头像名称
     * @return 头像名称
     */
    String selectPhotonameByYhid(String Yhid);
}