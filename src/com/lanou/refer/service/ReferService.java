package com.lanou.refer.service;

import com.lanou.base_utils.util.PageBean;
import com.lanou.refer.domain.Refer;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/28.
 */
public interface ReferService {
    List<Refer> findAll();

    List<Refer> find(Map<String, Object> params);

    void save(Refer referDriven);

    Refer findSingle(Class<Refer> referClass, String referId);

    void update(Refer referDriven);

    PageBean<Refer> findByPage(String status, int pageNum, int pageSize);

    PageBean<Refer> findByCD(List<Object> params, int pageNum, int pageSize);

}
