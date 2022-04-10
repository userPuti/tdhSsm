package com.tdh.service;

import com.tdh.domain.Depart;

import java.util.List;

/**
 * @author Puti
 * @date 2022/4/7 8:14
 */
public interface DepartService {
    /**
     * 查询部门信息
     *
     * @return 部门对象
     */
    List<Depart> queryDepart();
}
