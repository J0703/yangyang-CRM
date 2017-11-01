package com.lanou.teach.service.impl;

import com.lanou.base_utils.util.PageBean;
import com.lanou.teach.dao.ClassesDao;
import com.lanou.teach.domain.Classes;
import com.lanou.teach.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/27.
 */
@Service("classesService")
public class ClassesServiceImpl implements ClassesService{

    @Autowired
    @Qualifier("classesDao")
    private ClassesDao classesDao;

    @Override
    public List<Classes> findAll() {
        String hql = "from Classes";
        return classesDao.findAll(hql);
    }

    @Override
    public PageBean<Classes> findByPage(int pageNum, int pageSize) {
        String hql = "select count(*) from Classes";
        String hql1 = "from Classes where 1=1";
        int totalRecord = classesDao.getTotalRecord(hql);
        PageBean<Classes> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);
        List<Classes> data = classesDao.findALL(hql1,pageBean.getStartIndex(),pageBean.getPageSize());
        pageBean.setData(data);
        return pageBean;
    }

    @Override
    public Classes findSingle(Class<Classes> classesClass, String classId) {
        return classesDao.get(classesClass,classId);
    }

    @Override
    public void update(Classes classes1) {
        classesDao.update(classes1);
    }

    @Override
    public void save(Classes classes2) {
        classesDao.save(classes2);
    }

    @Override
    public List<Classes> findByCourse(Map<String, Object> params) {
        String hql = "from Classes where lessonTypeId =:courseTypeId";
        return classesDao.find(hql,params);
    }
}
