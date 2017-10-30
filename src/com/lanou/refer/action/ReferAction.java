package com.lanou.refer.action;

import com.lanou.hr.domain.Staff;
import com.lanou.hr.util.PageBean;
import com.lanou.refer.domain.Refer;
import com.lanou.refer.service.ReferService;
import com.lanou.teach.domain.Classes;
import com.lanou.teach.domain.CourseType;
import com.lanou.teach.service.ClassesService;
import com.lanou.teach.service.CourseTypeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.*;

/**
 * Created by dllo on 17/10/28.
 */
@Controller("referAction")
public class ReferAction extends ActionSupport implements ModelDriven<Refer> {

    @Autowired
    @Qualifier("referService")
    private ReferService referService;
    @Autowired
    @Qualifier("courseTypeService")
    private CourseTypeService courseTypeService;
    @Autowired
    @Qualifier("classesService")
    private ClassesService classesService;
    private List<Refer> referList;
    private Refer referDriven;
    private String courseTypeId;
    private String classId;
    private Refer refer;
    private List<CourseType> courseTypes;
    private List<Classes> classesList;
    private String condition;


    private int pageNum;
    private int pageSize = 3;
    /**
     * 分页查询获得post集合
     */
    public String findByPage1(){
        if (pageNum == 0){
            pageNum = 1;
        }
        ServletActionContext.getRequest().setAttribute("status","1");
        PageBean<Refer> pageBean = referService.findByPage("1",pageNum,pageSize);
        ActionContext.getContext().put("pageBean",pageBean);
        return SUCCESS;
    }
    public String findByPage2(){
        if (pageNum == 0){
            pageNum = 1;
        }
        ServletActionContext.getRequest().setAttribute("status","2");
        PageBean<Refer> pageBean = referService.findByPage("2",pageNum,pageSize);
        ActionContext.getContext().put("pageBean",pageBean);
        return SUCCESS;
    }

    public String findByCD(){
        System.out.println(condition);
        List<Object> params = new ArrayList<>();
        params.add(condition);
        if (pageNum == 0) {
            pageNum = 1;
        }
        PageBean<Refer> pageBean = referService.findByCD(params, pageNum, pageSize);
        ActionContext.getContext().put("pageBean", pageBean);
        return SUCCESS;
    }


    public String show() {
        refer = referService.findSingle(Refer.class, referDriven.getReferId());
        return SUCCESS;
    }

    public String findByEdit(){
        courseTypes = courseTypeService.findAll();
        refer = referService.findSingle(Refer.class, referDriven.getReferId());
        Map<String, Object> params = new HashMap<>();
        params.put("courseTypeId",refer.getCourseType().getCourseTypeId());
        classesList = classesService.findByCourse(params);
        return SUCCESS;
    }

    public String update(){
        Classes classes = classesService.findSingle(Classes.class, classId);
        CourseType courseType = courseTypeService.findSingle(CourseType.class, courseTypeId);
        Refer refer = referService.findSingle(Refer.class, referDriven.getReferId());
        refer.setName(referDriven.getName());
        refer.setTelephone(referDriven.getTelephone());
        refer.setQq(referDriven.getQq());
        refer.setIntentionLevel(referDriven.getIntentionLevel());
        refer.setSource(referDriven.getSource());
        refer.setRemark(referDriven.getRemark());
        if (classId.equals("-1")) {
            refer.setCourseType(courseType);
        } else {
            refer.setClasses(classes);
            refer.setCourseType(classes.getCourseType());
        }
        referService.update(refer);
        return SUCCESS;
    }

    public String add() {
        Classes classes = classesService.findSingle(Classes.class, classId);
        CourseType courseType = courseTypeService.findSingle(CourseType.class, courseTypeId);
        if (classId.equals("-1")) {
            referDriven.setCourseType(courseType);
        } else {
            referDriven.setClasses(classes);
            referDriven.setCourseType(classes.getCourseType());
        }
        Staff staff = (Staff) ServletActionContext.getRequest().getServletContext().getAttribute("adminStaff");
        referDriven.setStaff(staff);
        referDriven.setCreateDate(new Date());
        referDriven.setStatus("1");
        referService.save(referDriven);
        return SUCCESS;
    }

    public void validateAdd() {
        if (StringUtils.isBlank(referDriven.getName())) {
            addActionError("姓名不能为空");
        }
    }

    public List<Refer> getReferList() {
        return referList;
    }

    public void setReferList(List<Refer> referList) {
        this.referList = referList;
    }

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Refer getRefer() {
        return refer;
    }

    public void setRefer(Refer refer) {
        this.refer = refer;
    }

    public List<CourseType> getCourseTypes() {
        return courseTypes;
    }

    public void setCourseTypes(List<CourseType> courseTypes) {
        this.courseTypes = courseTypes;
    }

    public List<Classes> getClassesList() {
        return classesList;
    }

    public void setClassesList(List<Classes> classesList) {
        this.classesList = classesList;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public Refer getModel() {
        referDriven = new Refer();
        return referDriven;
    }
}
