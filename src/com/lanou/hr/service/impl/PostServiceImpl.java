package com.lanou.hr.service.impl;

import com.lanou.hr.dao.PostDao;
import com.lanou.hr.domain.Post;
import com.lanou.hr.service.PostService;
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
    public List<Post> findAll(String hql) {
        return postDao.findAll(hql);
    }

    @Override
    public List<Post> find(String hql, Map<String, Object> params) {
        return postDao.find(hql, params);
    }

    @Override
    public Post findSingle(String hql, Map<String, Object> params) {
        return postDao.findSingle(hql, params);
    }

    @Override
    public Post get(Class<Post> postClass, Serializable id) {
        return postDao.get(postClass, id);
    }

    @Override
    public void update(Post post) {
        postDao.update(post);
    }

    @Override
    public void save(Post post) {
        postDao.save(post);
    }
}
