package com.lanou.hr.service.impl;

import com.lanou.hr.dao.BaseDao;
import com.lanou.hr.service.BaseService;
import com.lanou.hr.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/26.
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    @Qualifier("baseDao")
    private BaseDao baseDao;
    @Override
    public List<T> findAll(String hql) {
        return baseDao.findAll(hql);
    }

    @Override
    public T findSingle(String hql, Map<String, Object> params) {
        return (T) baseDao.findSingle(hql,params);
    }

    @Override
    public List<T> find(String hql, Map<String, Object> params) {

        return baseDao.find(hql, params);
    }

    @Override
    public void update(T t) {
        baseDao.update(t);
    }

    @Override
    public void save(T t) {
        baseDao.save(t);
    }

    @Override
    public T get(Class<T> tClass, Serializable id) {
        return (T) baseDao.get(tClass,id);
    }

    @Override
    public PageBean<T> findByPage(String hql, String hql1, int pageNum, int pageSize) {
        int totalRecord = baseDao.getTotalRecord(hql);
        PageBean<T> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);
        List<T> data = baseDao.findALL(hql1,pageBean.getStartIndex(),pageBean.getPageSize());
        pageBean.setData(data);
        return pageBean;
    }
}
