package com.lanou.teach.service;

import com.lanou.base_utils.util.PageBean;
import com.lanou.teach.domain.Classes;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/27.
 */
public interface ClassesService {
    List<Classes> findAll();

    PageBean<Classes> findByPage(int pageNum, int pageSize);

    Classes findSingle(Class<Classes> classesClass, String classId);

    void update(Classes classes1);

    void save(Classes classes2);

    List<Classes> findByCourse(Map<String, Object> params);
}
