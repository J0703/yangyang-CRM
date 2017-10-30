package com.lanou.teach.service.impl;

import com.lanou.hr.util.PageBean;
import com.lanou.teach.dao.CourseTypeDao;
import com.lanou.teach.domain.CourseType;
import com.lanou.teach.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Service("courseTypeService")
public class CourseTypeServiceImpl implements CourseTypeService {

    @Autowired
    @Qualifier("courseTypeDao")
    private CourseTypeDao courseTypeDao;

    @Override
    public List<CourseType> findAll() {
        String hql = "from CourseType";
        return courseTypeDao.findAll(hql);
    }

    @Override
    public CourseType findSingle(Class<CourseType> courseTypeClass, String courseTypeId) {
        return courseTypeDao.get(courseTypeClass,courseTypeId);
    }

    @Override
    public void save(CourseType courseTypeDriven) {
        courseTypeDao.save(courseTypeDriven);
    }

    @Override
    public void update(CourseType courseTypeDriven) {
        courseTypeDao.update(courseTypeDriven);
    }

    @Override
    public PageBean<CourseType> findByPage(int pageNum, int pageSize) {
        String hql = "select count(*) from CourseType";
        String hql1 = "from CourseType where 1=1";
        int totalRecord = courseTypeDao.getTotalRecord(hql);
        PageBean<CourseType> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);
        List<CourseType> data = courseTypeDao.findALL(hql1,pageBean.getStartIndex(),pageBean.getPageSize());
        pageBean.setData(data);
        return pageBean;
    }

    @Override
    public PageBean<CourseType> findByCD(List<Object> params, int pageNum, int pageSize) {
        String hql = "select count(*) from CourseType where 1=1";
        String hql1 = "from CourseType where 1=1";
        int totalRecord = courseTypeDao.getTotalRecordStaff(hql,params);
        PageBean<CourseType> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);
        List<CourseType> data = courseTypeDao.findByCD(hql1,params,pageBean.getStartIndex(),pageBean.getPageSize());
        pageBean.setData(data);
        return pageBean;
    }
}
