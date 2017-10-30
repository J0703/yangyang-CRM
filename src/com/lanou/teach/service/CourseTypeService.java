package com.lanou.teach.service;


import com.lanou.hr.util.PageBean;
import com.lanou.teach.domain.CourseType;

import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
public interface CourseTypeService {


    List<CourseType> findAll();

    CourseType findSingle(Class<CourseType> courseTypeClass, String courseTypeId);

    void save(CourseType courseTypeDriven);

    void update(CourseType courseTypeDriven);

    PageBean<CourseType> findByPage(int pageNum, int pageSize);

    PageBean<CourseType> findByCD(List<Object> params, int pageNum, int pageSize);
}
