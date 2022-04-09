package com.tdh.mapper;

import com.tdh.domain.Bzdm;
import com.tdh.domain.BzdmExample;
import com.tdh.domain.BzdmKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BzdmMapper {
    long countByExample(BzdmExample example);

    int deleteByExample(BzdmExample example);

    int deleteByPrimaryKey(BzdmKey key);

    int insert(Bzdm record);

    int insertSelective(Bzdm record);

    List<Bzdm> selectByExample(BzdmExample example);

    Bzdm selectByPrimaryKey(BzdmKey key);

    int updateByExampleSelective(@Param("record") Bzdm record, @Param("example") BzdmExample example);

    int updateByExample(@Param("record") Bzdm record, @Param("example") BzdmExample example);

    int updateByPrimaryKeySelective(Bzdm record);

    int updateByPrimaryKey(Bzdm record);
}