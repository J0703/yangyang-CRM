package com.lanou.hr.service;

import com.lanou.hr.domain.Staff;
import com.lanou.hr.util.PageBean;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
public interface StaffService extends BaseService<Staff>{

    /**
     * 高级查询
     * @param params 参数
     * @return
     */
    PageBean<Staff> findByCD(String hql, String hql1, List<Object> params, int pageNum, int pageSize);

}
