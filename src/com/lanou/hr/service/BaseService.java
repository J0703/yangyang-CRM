package com.lanou.hr.service;

import com.lanou.hr.util.PageBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/26.
 */
public interface BaseService<T> {
    List<T> findAll(String hql);

    T findSingle(String hql, Map<String, Object> params);

    List<T> find(String hql, Map<String, Object> params);
    void update(T department);

    void save(T t);

    T get(Class<T> departmentClass, Serializable id);

    PageBean<T> findByPage(String hql, String hql1, int pageNum, int pageSize);
}
