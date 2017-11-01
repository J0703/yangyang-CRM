package com.lanou.hr.service;

import com.lanou.hr.domain.Staff;
import com.lanou.base_utils.util.PageBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
public interface StaffService {

    List<Staff> findAll();

    Staff findSingle(Map<String, Object> params);

    List<Staff> find(String hql, Map<String, Object> params);

    void update(Staff staff);

    void save(Staff staff);

    Staff get(Class<Staff> staffClass, Serializable id);

    PageBean<Staff> findByPage(int pageNum, int pageSize);

    /**
     * 高级查询
     *
     * @param params 参数
     * @return
     */
    PageBean<Staff> findByCD(List<Object> params, int pageNum, int pageSize);

    Staff login(Map<String, Object> params1);
}
