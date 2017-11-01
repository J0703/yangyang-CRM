package com.lanou.teach.action;

import com.lanou.base_utils.util.PageBean;
import com.lanou.teach.domain.Classes;
import com.lanou.teach.domain.CourseType;
import com.lanou.teach.service.ClassesService;
import com.lanou.teach.service.CourseTypeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/27.
 */
@Controller("classesAction")
@Scope("prototype")
public class ClassesAction extends ActionSupport implements ModelDriven<Classes> {
    @Autowired
    @Qualifier("classesService")
    private ClassesService classesService;
    @Autowired
    @Qualifier("courseTypeService")
    private CourseTypeService courseTypeService;
    private List<Classes> classesList;
    private Classes classesDriven;
    private String courseTypeId;

    private File schedule;
    private String scheduleFileName;
    private String scheduleContentType;
    private String filename;
    private InputStream inputStream;

    private int pageNum;
    private int pageSize = 3;
    private Classes classes;
    private List<CourseType> courseTypes;

    /**
     * 分页查询
     */
    public String findByPage() {
        if (pageNum == 0) {
            pageNum = 1;
        }
        PageBean<Classes> pageBean = classesService.findByPage(pageNum, pageSize);
        ActionContext.getContext().put("pageBean", pageBean);
        return SUCCESS;
    }

    /**
     * 查询所有
     */
    public String findAll() {
        classesList = classesService.findAll();
        return SUCCESS;
    }

    /**
     * 查询单个对象
     */
    public String findSingle() {
        classes = classesService.findSingle(Classes.class, classesDriven.getClassId());
        courseTypes = courseTypeService.findAll();
        return SUCCESS;
    }

    /**
     * 获取班级信息
     */
    public String showClasses(){
        classes = classesService.findSingle(Classes.class, classesDriven.getClassId());
        return SUCCESS;
    }

    /**
     * 获取班级信息
     */
    public String findByUpload() {
        classes = classesService.findSingle(Classes.class, classesDriven.getClassId());
        return SUCCESS;
    }

    /**
     * 上传
     */
    public String upload() {
        String path = ServletActionContext.getServletContext().getRealPath("classable");
        File destDirectory = new File(path);
        if (destDirectory.exists() || !destDirectory.isDirectory()) {
            destDirectory.mkdirs();
        }
        File file = new File(destDirectory, scheduleFileName);
        try {
            FileUtils.copyFile(schedule, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Classes classes1 = classesService.findSingle(Classes.class, classesDriven.getClassId());
        classes1.setUploadFileName(scheduleFileName);
        classes1.setUploadPath(path);
        classes1.setUploadTime(new Date());
        classesService.update(classes1);
        return SUCCESS;
    }

    /**
     * 下载
     */
    public String download(){
        String dirPath = ServletActionContext.getServletContext().getRealPath("classable");
        File file = new File(dirPath, filename);
        try {
            inputStream = new FileInputStream(file);
            filename = filenameEncoding(filename,ServletActionContext.getRequest(),ServletActionContext.getResponse());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 通过课程查班级
     */
    public String findByCourse(){
        Map<String, Object> params = new HashMap<>();
        params.put("courseTypeId",courseTypeId);
        classesList = classesService.findByCourse(params);
        return SUCCESS;
    }

    /**
     * 添加或修改
     */
    public String addOrEdit() {
        CourseType courseType = courseTypeService.findSingle(CourseType.class, courseTypeId);
        if (!StringUtils.isBlank(classesDriven.getClassId())) {
            Classes classes1 = classesService.findSingle(Classes.class, classesDriven.getClassId());
            classes1.setName(classesDriven.getName());
            classes1.setCourseType(courseType);
            classes1.setBeginTime(classesDriven.getBeginTime());
            classes1.setEndTime(classesDriven.getEndTime());
            classes1.setRemark(classesDriven.getRemark());
            classesService.update(classes1);
        } else {
            Classes classes2 = new Classes(courseType, classesDriven.getName(),
                    classesDriven.getBeginTime(), classesDriven.getEndTime(), classesDriven.getRemark());
            classesService.save(classes2);
        }
        return SUCCESS;
    }

    /**
     * 添加修改验证器
     */
    public void validateAddOrEdit() {
        if (StringUtils.isBlank(classesDriven.getName())) {
            addActionError("班级姓名不能为空");
        }
    }

    /**
     * 处理浏览器文件下载包含中文的问题
     * filename 要进行中文处理的原文件名称
     * 返回处理完成的文件名称
     */
    public String filenameEncoding(String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String agent = request.getHeader("User-Agent");
        //  System.out.println(agent);
        if (agent.contains("Firefox")) {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
        } else if (agent.contains("MSIE")) {
            filename = URLEncoder.encode(filename, "utf-8");
        } else if (agent.contains("Safari")) {
            filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
        } else {
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
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

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public List<CourseType> getCourseTypes() {
        return courseTypes;
    }

    public void setCourseTypes(List<CourseType> courseTypes) {
        this.courseTypes = courseTypes;
    }

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public File getSchedule() {
        return schedule;
    }

    public void setSchedule(File schedule) {
        this.schedule = schedule;
    }

    public String getScheduleFileName() {
        return scheduleFileName;
    }

    public void setScheduleFileName(String scheduleFileName) {
        this.scheduleFileName = scheduleFileName;
    }

    public String getScheduleContentType() {
        return scheduleContentType;
    }

    public void setScheduleContentType(String scheduleContentType) {
        this.scheduleContentType = scheduleContentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public Classes getModel() {
        classesDriven = new Classes();
        return classesDriven;
    }
}
