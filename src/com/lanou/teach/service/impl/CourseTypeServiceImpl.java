package com.lanou.teach.service.impl;

import com.lanou.base_utils.util.PageBean;
import com.lanou.teach.dao.CourseTypeDao;
import com.lanou.teach.domain.CourseType;
import com.lanou.teach.service.CourseTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        StringBuffer stringBuffer = new StringBuffer(hql);
        StringBuffer stringBuffer1 = new StringBuffer(hql1);
        String courseName = (String) params.get(0);
        String remark = (String) params.get(1);
        int totalStart = StringUtils.isBlank(params.get(2).toString()) ? 0 : Integer.parseInt(params.get(2).toString());
        int totalEnd = StringUtils.isBlank(params.get(3).toString()) ? 0 : Integer.parseInt(params.get(3).toString());
        double lessonCostStart = StringUtils.isBlank(params.get(4).toString()) ? 0 : Double.parseDouble(params.get(4).toString());
        double lessonCostEnd = StringUtils.isBlank(params.get(5).toString()) ? 0 : Double.parseDouble(params.get(5).toString());
        List<Object> lists = new ArrayList<>();
        if (!StringUtils.isBlank(courseName)) {
            stringBuffer.append(" and courseName like ?");
            stringBuffer1.append(" and courseName like ?");
            lists.add("%" + courseName + "%");
        }
        if (!StringUtils.isBlank(remark)) {
            stringBuffer.append(" and remark like ? ");
            stringBuffer1.append(" and remark like ? ");
            lists.add("%" + remark + "%");
        }
        stringBuffer.append(" and total > " + totalStart + "");
        stringBuffer1.append(" and total > " + totalStart + "");
        if (totalEnd > 0) {
            stringBuffer.append(" and total < " + totalEnd + "");
            stringBuffer1.append(" and total < " + totalEnd + "");
        }
        stringBuffer1.append(" and courseCost > " + lessonCostStart + "");
        stringBuffer.append(" and courseCost > " + lessonCostStart + "");
        if (lessonCostEnd > 0) {
            stringBuffer.append(" and courseCost < " + lessonCostEnd + "");
            stringBuffer1.append(" and courseCost < " + lessonCostEnd + "");
        }
        int totalRecord = courseTypeDao.getTotalRecordT(stringBuffer.toString(),lists);
        PageBean<CourseType> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);
        List<CourseType> data = courseTypeDao.findByCD(stringBuffer1.toString(),lists,pageBean.getStartIndex(),pageBean.getPageSize());
        pageBean.setData(data);
        return pageBean;
    }
}
