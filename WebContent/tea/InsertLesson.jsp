<%@ page language="java" import="java.util.*, com.ten.dao.*,com.ten.user.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <meta charset="UTF-8">
    <title>学生选课信息管理系统-添加课程</title>
    <link href="style_1.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" href="style2.css" type="text/css" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

</head>
<body>
<%
    Teacher tea = null;
    if(session.getAttribute("teacher")==null){
        response.sendRedirect("/WebContent/Login.jsp");
    }else{
        tea = (Teacher)session.getAttribute("teacher");}
%>
<div class="top">
    <div class="title"><p> 学生选课信息管理系统</p></div>
</div>
<div class="main">
    <div class="main_left">
        <div class="main_left_class00"><img src="img/touxiang.png" class="xwcms" onmouseover="this.src='img/touxiang.png'"
                                            onmouseout="this.src='img/touxiang.png'" /></div>
        <div class="main_left_class01"><a class="herf" href="tea/selectCou.jsp"style='color:white'>选课管理</a></div>
        <div class="main_left_class02"><a class="herf" href="tea/selectStu.jsp"style='color:white'>学生信息</a></div>
        <div class="main_left_class03"><a class="herf" href="Query.jsp" style='color:white'>信息查询</a></div>
        <div class="main_left_class100">
            <input class="reset" type="button" value="注销" onClick="window.location.href=('/WebContent/servlet/serDoLogout')">
        </div>
    </div>
    <div class="main_right">
        <div class="info">
            <p>亲爱的<%= tea.getTname() %>老师，下午好</p>
        </div>
        <div class="box">
            <div class="function"><p>[通知]选课系统已开放</p></div>
            <div class="form">
                <div class="form_1">
                    <%
                        DaoCou selectone = new DaoCou();
                        Course cou = selectone.selectCou(request.getParameter("cid"),0);
                        if(cou!=null){
                    %>
                    <form action="servlet/serDoInsertLesson" method="post">
                        <table>

                            <tr><td>课程编号</td> <td><input  class="text" type="text" name="CNo" value="<%= cou.getCNo() %>" readonly="true"/></td></tr>
                            <tr><td>课程名称</td> <td><input  class="text" type="text" name="Cname" value="<%= cou.getCname() %>" readonly="true"/></td></tr>
                            <tr><td>课程学分</td> <td><input  class="text" type="text" name="Ccredit" value="<%= cou.getCcredit() %>" readonly="true"/></td></tr>
                            <tr><td>教学班编号</td> <td><input  class="text" type="text" name="Lessonno" value="<%= cou.getLessonNo()== "null" ? cou.getLessonNo() : "" %>"/></td></tr>
                            <tr><td>开课时间</td> <td><input  class="text" type="text" name="Classtime" value="<%= cou.getCtime()=="null" ? cou.getCtime(): "  "%>"/></td></tr>
                            <tr><td>开课地点</td> <td><input  class="text" type="text" name="Classroom" value="<%= cou.getClassroom()=="null"? cou.getClassroom(): ""%>"/></td></tr>
                            <tr><td>授课老师</td> <td><input  class="text" type="text" name="Cteacher" value="<%= cou.getCteacher()=="null" ? cou.getCteacher(): ""%>"/></td></tr>
                            <tr><td>开课班级最大人数</td> <td><input  class="text" type="text" name="Maxnum" value="<%= cou.getMaxnum()=="null" ? cou.getMaxnum() : ""%>"/></td></tr>
                            <tr><td></td><td><input class="btn" type="submit" value="添加教学班"/>  </td></tr>

                        </table>
                    </form>
                    <%
                        }
                    %>
                </div>
            </div>

        </div>
    </div>
</div>
</body>

</html>
