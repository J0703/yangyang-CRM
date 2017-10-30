package com.lanou.teach.dao;

import com.lanou.hr.dao.BaseDao;
import com.lanou.teach.domain.CourseType;

import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
public interface CourseTypeDao extends BaseDao<CourseType>{
    int getTotalRecordStaff(String hql, List<Object> params);

    List<CourseType> findByCD(String hql1, List<Object> params, int startIndex, int pageSize);
}
