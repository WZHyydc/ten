<%@ page language="java" import="java.util.*,com.ten.user.Teacher" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
	<title>学生选课信息管理系统-后台管理</title>
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
	  		tea = (Teacher)session.getAttribute("teacher");
	   %>
	<div class="top">
		<div class="title"><p> 学生选课信息管理系统 </p></div>
	</div>
	<div class="main">
		<div class="main_left">
			<div class="main_left_class00"><img src="img/touxiang.png" class="xwcms" onmouseover="this.src='img/touxiang.png'"
             onmouseout="this.src='img/touxiang.png'" /></div>
			<div class="main_left_class01"><a class="herf" href="tea/selectCou.jsp"style='color:white'>选课管理</a></div>
			<div class="main_left_class02"><a class="herf" href="tea/selectStu.jsp"style='color:white'>学生信息</a></div>
			<div class="main_left_class03"><a class="herf" href="tea/Query.jsp"style='color:white'>信息查询</a></div>
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
				<table class="news">
			<tr><td><h2>关于本学期校级任选课相关事宜安排的通知</h2></td></tr>
			<tr><td>各学院及相关单位:</td></tr>
			<tr><td>根据学校教学安排，本学期全校任选课定于2023年9月13日 （星期一） 正式开课，选课工作将从2023年8月25日开始。现将有关事项通知如下：</td></tr>
			<tr><td>一、选课时间：2023年8月25日下午14：00-18：00</td></tr>
			<tr><td>二、学生登录选课信息管理系统后进入学生选课进行选课。学生选课前请认真阅读选课公告，再进行“网上选课”。</td></tr>
			<tr><td>三、教师可登陆选课信息管理系统后进入选课管理增删课程，2023年8月25日18:00之后各位教师及学生登录教务处网站查询自己申报的课程是否停开。</td> </tr>
			<tr><td>四、选课退课均须在选课时间内完成，选课结束后不再进行补退选，请学生注意选课时间。</td></tr>
			<tr><td>五、请各学院通知并组织学生进行选课，同时请各学院及相关单位通知任课教师按时上课。教务处将组织相关人员对上课情况进行不定期的检查。</td></tr>
			<tr><td align="right">2023-08-20</td></tr>
			</table>
				</div>
			</div>
		</div>
	</div>
	<%} %>
	</body>
</html>
