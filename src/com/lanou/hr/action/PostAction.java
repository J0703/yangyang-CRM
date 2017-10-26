package com.lanou.hr.action;

import com.lanou.hr.domain.Department;
import com.lanou.hr.domain.Post;
import com.lanou.hr.service.DepartmentService;
import com.lanou.hr.service.PostService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
@Controller("postAction")
public class PostAction extends ActionSupport {

    @Autowired
    @Qualifier("postService")
    private PostService postService;

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;
    private List<Post> posts;
    private String depId;
    private String postId;
    private Post post;
    private String postName;


    /**
     * 获取职务集合
     *
     * @return
     */
    public String findPost() {
        String hql = "from Post";
        posts = postService.findAll(hql);
        return SUCCESS;
    }

    /**
     * 通过部门id获取职务集合
     *
     * @return
     */
    public String showPost() {
        String hql = "from Post where depId=:depId";
        Map<String, Object> params = new HashMap<>();
        params.put("depId", depId);
        posts = postService.find(hql, params);
        return SUCCESS;
    }

    public String findSingle() {
        Serializable id = postId;
        post = postService.get(Post.class, id);
        System.out.println(post.getDepartment());
        return SUCCESS;
    }

    /**
     * 保存获得跟新post, 通过部门id获取department对象
     * @return
     */
    public String save_update(){

        Department department = departmentService.get(Department.class, depId);
        if (StringUtils.isBlank(postId)){
            Post post = new Post();
            post.setPostName(postName);
            post.setDepartment(department);
            postService.save(post);
        }else {
            Post post = new Post(postId,postName,department);
            postService.update(post);
        }
        return SUCCESS;
    }


    public void validateSave_update() {
        if (StringUtils.isBlank(postName)){
            addActionError("输入的职务名不能为空");
        }
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}
