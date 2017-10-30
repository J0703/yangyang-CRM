package com.lanou.refer.dao;

import com.lanou.hr.dao.BaseDao;
import com.lanou.refer.domain.Refer;

import java.util.List;

/**
 * Created by dllo on 17/10/28.
 */
public interface ReferDao extends BaseDao<Refer>{
    int getTotalRecordRefer(String hql, List<Object> params);

    List<Refer> findByCD(String hql1, List<Object> params, int startIndex, int pageSize);
}
