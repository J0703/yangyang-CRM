package com.lanou.teach.action;


import com.lanou.hr.util.PageBean;
import com.lanou.teach.domain.CourseType;
import com.lanou.teach.service.CourseTypeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Controller("courseTypeAction")
public class CourseTypeAction extends ActionSupport implements ModelDriven<CourseType> {
    @Autowired
    @Qualifier("courseTypeService")
    private CourseTypeService courseTypeService;
    private List<CourseType> courseTypes;

    private CourseType courseTypeDriven;
    private CourseType courseType;
    private int pageNum;
    private int pageSize = 3;

    private String totalStart;
    private String totalEnd;
    private String lessonCostStart;
    private String lessonCostEnd;


    /**
     * 高级查询分页
     * @return
     */
    public String findByCD() {
        List<Object> params = new ArrayList<>();
        params.add(courseTypeDriven.getCourseName());
        params.add(courseTypeDriven.getRemark());
        params.add(totalStart);
        params.add(totalEnd);
        params.add(lessonCostStart);
        params.add(lessonCostEnd);
        if (pageNum == 0) {
            pageNum = 1;
        }
        PageBean<CourseType> pageBean = courseTypeService.findByCD(params, pageNum, pageSize);
        ActionContext.getContext().put("pageBean", pageBean);
        return SUCCESS;
    }

    /**
     * 高级查询表单校验
     */
    public void validateFindByCD() {
        if (!StringUtils.isBlank(totalStart)){
            if (!NumberUtils.isNumber(totalStart)){
                addActionError("输入的数据不合法");
            }
        }
        if (!StringUtils.isBlank(totalEnd)){
            if (!NumberUtils.isNumber(totalEnd)){
                addActionError("输入的数据不合法");
            }
        }
        if (!StringUtils.isBlank(lessonCostEnd)){
            if (!NumberUtils.isNumber(lessonCostEnd)){
                addActionError("输入的数据不合法");
            }
        }
        if (!StringUtils.isBlank(lessonCostStart)){
            if (!NumberUtils.isNumber(lessonCostStart)){
                addActionError("输入的数据不合法");
            }
        }

    }

    /**
     * 分页查询
     * @return
     */
    public String findByPage() {
        if (pageNum == 0) {
            pageNum = 1;
        }
        PageBean<CourseType> pageBean = courseTypeService.findByPage(pageNum, pageSize);
        ActionContext.getContext().put("pageBean", pageBean);
        return SUCCESS;
    }


    /**
     * 查询所有
     * @return
     */
    public String findAll() {
        courseTypes = courseTypeService.findAll();
        return SUCCESS;
    }

    /**
     * 通过id查询单个对象
     * @return
     */
    public String findSingle() {
        courseType = courseTypeService.findSingle(CourseType.class, courseTypeDriven.getCourseTypeId());
        return SUCCESS;
    }

    /**
     * 增加或编辑
     * @return
     */
    public String addOrEdit() {
        System.out.println(courseTypeDriven);
        if (!StringUtils.isBlank(courseTypeDriven.getCourseTypeId())) {
            courseTypeService.update(courseTypeDriven);
        } else {
            courseTypeService.save(courseTypeDriven);
        }
        return SUCCESS;
    }

    public void validateAddOrEdit() {
        if (StringUtils.isBlank(courseTypeDriven.getCourseName()) || StringUtils.isBlank(courseTypeDriven.getCourseCost().toString()) ||
                courseTypeDriven.getTotal() <= 0) {
            addActionError("输入的表单数据不能为空");
        }
    }

    public List<CourseType> getCourseTypes() {
        return courseTypes;
    }

    public void setCourseTypes(List<CourseType> courseTypes) {
        this.courseTypes = courseTypes;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    @Override
    public CourseType getModel() {
        courseTypeDriven = new CourseType();
        return courseTypeDriven;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getTotalStart() {
        return totalStart;
    }

    public void setTotalStart(String totalStart) {
        this.totalStart = totalStart;
    }

    public String getTotalEnd() {
        return totalEnd;
    }

    public void setTotalEnd(String totalEnd) {
        this.totalEnd = totalEnd;
    }

    public String getLessonCostStart() {
        return lessonCostStart;
    }

    public void setLessonCostStart(String lessonCostStart) {
        this.lessonCostStart = lessonCostStart;
    }

    public String getLessonCostEnd() {
        return lessonCostEnd;
    }

    public void setLessonCostEnd(String lessonCostEnd) {
        this.lessonCostEnd = lessonCostEnd;
    }
}
