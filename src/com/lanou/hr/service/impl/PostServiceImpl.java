package com.lanou.hr.service.impl;


import com.lanou.hr.dao.PostDao;
import com.lanou.hr.domain.Post;
import com.lanou.hr.service.PostService;
import com.lanou.base_utils.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * Created by dllo on 17/10/25.
 */
@Service("postService")
public class PostServiceImpl implements PostService {
    @Autowired
    @Qualifier("postDao")
    private PostDao postDao;

    @Override
    public List<Post> find(Map<String, Object> params) {
        String hql = "from Post where depId=:depId";
        return postDao.find(hql, params);
    }

    @Override
    public void update(Post post) {
        postDao.update(post);
    }

    @Override
    public void save(Post post) {
        postDao.save(post);
    }

    @Override
    public Post get(Class<Post> tClass, Serializable id) {
        return postDao.get(tClass, id);
    }

    @Override
    public PageBean<Post> findByPage(int pageNum, int pageSize) {
        String hql = "select count(*) from Post";
        String hql1 = "from Post where 1=1";
        int totalRecord = postDao.getTotalRecord(hql);
        PageBean<Post> pageBean = new PageBean<>(pageNum, pageSize, totalRecord);
        List<Post> data = postDao.findALL(hql1, pageBean.getStartIndex(), pageBean.getPageSize());
        pageBean.setData(data);
        return pageBean;
    }

}
