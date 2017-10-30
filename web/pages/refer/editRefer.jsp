<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>

    <link href="${pageContext.request.contextPath}/css/sys.css" type="text/css" rel="stylesheet"/>

</head>

<body class="emp_body">
<table border="0" cellspacing="0" cellpadding="0" width="100%">
    <tr>
        <td class="topg"></td>
    </tr>
</table>

<table border="0" cellspacing="0" cellpadding="0" class="wukuang" width="100%">
    <tr>
        <td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
        <td width="44%" align="left">[添加/编辑咨询学生]</td>

        <td width="52%" align="right">
            <%--保存 --%>
            <a href="javascript:void(0)" onclick="document.forms[0].submit()"><img
                    src="${pageContext.request.contextPath}/images/button/save.gif"/></a>
            <a href="#"><img src="${pageContext.request.contextPath}/images/button/tuihui.gif"/></a>
        </td>
        <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
    </tr>
</table>

<form action="${pageContext.request.contextPath}/refer/updateRefer.action?referId=${refer.referId}" method="post">
    <table width="89%" class="emp_table" style="" align="left" cellspacing="0">
        <tr>
            <td width="120px" height="35" align="left">姓名：</td>
            <td width="300px" align="left">
                <input type="text" name="name" value="${refer.name}"/>
            </td>
            <td>

            </td>
        </tr>
        <tr>
            <td>电话：</td>
            <td><input type="text" name="telephone" value="${refer.telephone}"/></td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>QQ：</td>
            <td><input type="text" name="qq" value="${refer.qq}"/></td>
            <td>&nbsp;</td>
        </tr>

        <tr>
            <td>意向级别：</td>
            <td>
                <select name="intentionLevel">
                    <option value="-1">--请选择意向级别--</option>

                    <option value="马上报名"  <c:if test="${refer.intentionLevel eq '马上报名'}">selected="selected"</c:if>>A.马上报名</option>
                    <option value="想报名，考虑中" <c:if test="${refer.intentionLevel eq '想报名，考虑中'}">selected="selected"</c:if>>B.想报名，考虑中</option>
                    <option value="有报名意向，但暂时不能报名" <c:if test="${refer.intentionLevel eq '有报名意向，但暂时不能报名'}">selected="selected"</c:if>>C.有报名意向，但暂时不能报名</option>
                    <option value="不能报名，以及其他原因" <c:if test="${refer.intentionLevel eq '不能报名，以及其他原因'}">selected="selected"</c:if>>D.不能报名，以及其他原因</option>
                </select>

            </td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>意向学科：</td>
            <td>
                <select name="courseTypeId" onchange="">
                    <option value="-1">----请--选--择----</option>
                    <s:iterator value="courseTypes" var="courseType">
                        <option value="${courseType.courseTypeId}" <c:if test="${courseType.courseTypeId eq refer.courseType.courseTypeId}">selected="selected"</c:if>>${courseType.courseName}</option>
                    </s:iterator>

                </select>

                &nbsp;&nbsp;&nbsp;意向班级：

                <select name="classId">
                    <option value="-1">----请--选--择----</option>
                    <s:iterator value="classesList" var="classes">
                        <option value="${classes.classId}" <c:if test="${classes.classId eq refer.classes.classId}">selected="selected"</c:if>>${classes.name}</option>
                    </s:iterator>
                </select>


            </td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td height="41" align="left">来源：</td>
            <td align="left">
                <select name="source">
                    <option value="-1">--请选择来源--</option>

                    <option value="QQ" <c:if test="${refer.source eq 'QQ'}">selected="selected"</c:if>>1.QQ</option>
                    <option value="电话咨询" <c:if test="${refer.source eq '电话咨询'}">selected="selected"</c:if>>2.电话咨询</option>
                    <option value="上门" <c:if test="${refer.source eq '上门'}">selected="selected"</c:if>>3.上门</option>
                    <option value="其他（老学员推荐)" <c:if test="${refer.source eq '其他（老学员推荐)'}">selected="selected"</c:if>>4.其他（老学员推荐）</option>
                </select>


            </td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td align="left">备注：</td>
            <td align="left">
                <textarea name="remark" cols="60" rows="5">${refer.remark}</textarea>
            </td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td colspan="3">&nbsp;</td>
        </tr>
    </table>
</form>

<script type="application/javascript">
    $(function () {
        <c:if test="${empty refer}">
        $.post("${pageContext.request.contextPath}/refer/findCourseJson.action", null,
                function (data) {
                    var html_ = '<option value="-1">----请--选--择----</option>'
                    $.each(data, function (index, value) {
                        html_ += '<option value="' + value.courseTypeId + '">' + value.courseName + '</option>'
                    })
                    $("#courseTypeId").html(html_)
                }, "json");
        </c:if>
        $("#courseTypeId").change(function () {
            $.post("${pageContext.request.contextPath}/refer/findClassJson.action",
                    {courseTypeId: $("#courseTypeId").val()},
                    function (data) {
                        var html_ = '<option value="-1">----请--选--择----</option>'
                        $.each(data, function (index, value) {
                            html_ += '<option value="' + value.classId + '">' + value.name + '</option>'
                        })
                        $("#classId").html(html_)
                    }, "json")
        })
    })

</script>
</body>
</html>
