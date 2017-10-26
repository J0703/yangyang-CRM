package com.lanou.hr.dao.impl;

import com.lanou.hr.dao.BaseDao;

import com.lanou.hr.util.PageHibernateCallback;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/24.
 */

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {


    /**
     * 查询所有
     *
     * @param hql 查询语句
     * @return
     */
    @Override
    public List<T> findAll(String hql) {
        Session session = currentSession();
        Query query = session.createQuery(hql);
        List<T> tList = query.list();
        return tList;
    }

    /**
     * 条件查询
     *
     * @param hql    查询语句
     * @param params 参数
     * @return
     */
    @Override
    public List<T> find(String hql, Map<String, Object> params) {
        Session session = currentSession();
        Query query = session.createQuery(hql);
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }
        List<T> tList = query.list();
        return tList;
    }

    /**
     * 条件查询
     *
     * @param hql    查询语句
     * @param params 参数
     * @return
     */
    @Override
    public T findSingle(String hql, Map<String, Object> params) {
        Session session = currentSession();
        Query query = session.createQuery(hql);
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }
        List<T> list = query.list();
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 通过id获取
     *
     * @param c  Class
     * @param id 序列化id
     * @return
     */
    @Override
    public T get(Class<T> c, Serializable id) {
        Session session = currentSession();
        // 根据主键id查询某个对象
        T t = (T) session.get(c, id);
        return t; // 返回查询到的t对象
    }

    /**
     * 更新
     *
     * @param t 更新对象
     */
    @Override
    public void update(T t) {
        Session session = currentSession();
        session.update(t);
    }

    /**
     * 保存
     *
     * @param t 保存对象
     */
    @Override
    public void save(T t) {
        Session session = currentSession();
        session.save(t);
    }

    @Override
    public int getTotalRecord(String hql) {
        List<Long> find = (List<Long>) this.getHibernateTemplate().find(hql);
        if (find != null) {
            return find.get(0).intValue();
        }
        return 0;
    }
    @Override
    public List<T> findALL(String hql, int startIndex, int pageSize) {
        return this.getHibernateTemplate().execute(new PageHibernateCallback<T>(hql,startIndex, pageSize));
    }
}
