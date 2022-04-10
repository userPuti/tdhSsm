package com.tdh.service.impl;

import com.tdh.domain.Depart;
import com.tdh.domain.DepartExample;
import com.tdh.mapper.DepartMapper;
import com.tdh.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Puti
 * @date 2022/4/7 8:15
 */
@Service("departService")
public class ImplDepartService implements DepartService {
//    private SqlSession sqlSession = SqlSessionUtils.getSqlSession();

    @Autowired
//    @Qualifier("departMapper")
    private DepartMapper departMapper;
    /**
     * 查询部门信息
     *
     * @return 部门对象
     */
    /**
     * 查询部门信息
     *
     * @return 部门对象
     */
    @Override
    public List<Depart> queryDepart() {
        DepartExample departExample = new DepartExample();
        departExample.createCriteria();
        return departMapper.selectByExample(departExample);
    }

}
