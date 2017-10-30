<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>

    <link href="${pageContext.request.contextPath}/css/sys.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript">
        function selectRefer() {

        }
    </script>
</head>

<body>
<table border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td class="topg"></td>
    </tr>
</table>

<table border="0" cellspacing="0" cellpadding="0" class="wukuang">
    <tr>
        <td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
        <td width="33%" align="left">[咨询学生管理]</td>

        <td width="63%" align="right">
            <%--添加咨询 --%>
            <a class="butbg" href="${pageContext.request.contextPath}/pages/refer/addRefer.jsp">
                <img src="${pageContext.request.contextPath}/images/button/tianjia.gif"/>
            </a>
        </td>
        <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
    </tr>
</table>

<!-- 查询条件：失去焦点马上查询 -->
<form action="${pageContext.request.contextPath}/refer/findByCD.action" method="post">
    <table width="88%" border="0" style="margin: 20px;">
        <tr>
            <td width="80px">查询条件：</td>
            <td width="300px">
                <input type="text" name="condition" size="20" onblur="findByCD()"/>
                （姓名|电话|QQ）
            </td>
            <td></td>
        </tr>
    </table>
</form>

<table border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
    <tr>
        <td><img src="${pageContext.request.contextPath}/images/result.gif"/></td>
    </tr>
</table>



<form action="${pageContext.request.contextPath}/refer/<c:if test="${status eq 1}">findByStatus1.action</c:if><c:if test="${status eq 2}">findByStatus2.action</c:if>" method="post">

    <table width="100%" border="1">

        <tr class="henglan" style="font-weight:bold;">
            <td width="80px" align="center">姓名</td>
            <td width="9%" align="center">电话</td>
            <td width="11%" align="center">QQ</td>
            <td width="15%" align="center">意向类别/班级</td>
            <td width="17%" align="center">状态</td>
            <td width="10%" align="center">营销人员</td>
            <td width="8%" align="center">查看</td>
            <%-- 根据状态不同显示操作项 --%>
            <td width="8%" align="center">编辑</td>
            <td width="8%" align="center">跟踪</td>
            <td width="8%" align="center">录入学籍</td>
        </tr>
        <s:iterator value="#pageBean.data" var="refer">

        <tr class="tabtd1">
            <td align="center">${refer.name}</td>
            <td align="center">${refer.telephone}</td>
            <td align="center">${refer.qq}</td>
            <td align="center">
                    ${refer.intentionLevel}/
                    ${refer.classes.name}
            </td>
            <td align="center">
                <c:choose>
                    <c:when test="${refer.status eq 1}">
                        咨询中
                    </c:when>
                    <c:otherwise>
                        已报名
                    </c:otherwise>
                </c:choose>

            </td>
            <td align="center">${refer.staff.staffName}</td>

            <!-- 查看 -->
            <td width="8%" align="center">
                <a href="${pageContext.request.contextPath}/refer/showRefer.action?referId=${refer.referId}"><img
                        src="${pageContext.request.contextPath}/images/button/view.gif" class="img"/></a>
            </td>


            <!-- 编辑 -->
            <td width="8%" align="center">
                <a href="${pageContext.request.contextPath}/refer/showReferEdit.action?referId=${refer.referId}"><img
                        src="${pageContext.request.contextPath}/images/button/modify.gif" class="img"/></a>
            </td>

            <!-- 添加跟踪 -->
            <td width="8%" align="center">
                <a href="${pageContext.request.contextPath}/refer/findSingle.action?referId=${refer.referId}"><img
                        src="${pageContext.request.contextPath}/images/button/new.gif" class="img"/></a>
            </td>

            <!-- 入学 -->
            <td width="8%" align="center">
                <a href="${pageContext.request.contextPath}/pages/refer/addStudent.jsp"><img
                        src="${pageContext.request.contextPath}/images/button/modify.gif" class="img"/></a>
            </td>
            </s:iterator>
        </tr>
    </table>
    <input id="pageNum" name="pageNum" type="hidden" value="<s:property value="pageBean.pageNum"/>">
</form>
<table border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td align="right">
            <span>第<s:property value="#pageBean.pageNum"/>/<s:property value="#pageBean.totalPage"/>页</span>
            <span>
                <s:if test="#pageBean.pageNum gt 1">
                    <a href="javascript:void(0)" onclick="showPage(1)">[首页]</a>&nbsp;&nbsp;
                    <a href="javascript:void(0)"
                       onclick="showPage(<s:property value="#pageBean.pageNum - 1"/>)">[上一页]</a>&nbsp;&nbsp;
                </s:if>

                <s:if test="#pageBean.pageNum lt #pageBean.totalPage">
                    <a href="javascript:void(0)"
                       onclick="showPage(<s:property value="#pageBean.pageNum + 1"/>)">[下一页]</a>&nbsp;&nbsp;
                    <a href="javascript:void(0)" onclick="showPage(<s:property value="#pageBean.totalPage"/>)">[尾页]</a>
                </s:if>
        </span>
        </td>
    </tr>
</table>
<script type="text/javascript">
    function showPage(num) {
        document.getElementById("pageNum").value = num;
        document.forms[1].submit();
    }

    function findByCD() {
        document.forms[0].submit();
    }
</script>
</body>
</html>
