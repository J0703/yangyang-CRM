package com.lanou.hr.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/24.
 */
public interface BaseDao<T> {
    /**
     * 查询所有
     * @param hql 查询语句
     * @return 结果集
     */
    List<T> findAll(String hql);

    /**
     * 条件查询
     * @param hql 查询语句
     * @param params 参数
     * @return 结果集
     */
    List<T> find(String hql, Map<String, Object> params);

    /**
     * 条件查询
     * @param hql 查询语句
     * @param params 参数
     * @return 单个对象
     */
    T findSingle(String hql, Map<String, Object> params);

    /**
     * 通过id获取
     * @param c Class
     * @param id 序列化id
     * @return 单个对象
     */
    T get(Class<T> c, Serializable id);

    /**
     * 更新
     * @param t 更新对象
     */
    void update(T t);

    /**
     * 保存
     * @param t 保存对象
     */
    void save(T t);

    int getTotalRecord(String hql);

    List<T> findALL(String hql,int startIndex, int pageSize);

}
