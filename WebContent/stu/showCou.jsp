<%@ page language="java" import="java.util.*,com.ten.dao.DaoSc,com.ten.user.Student,com.ten.user.SedCou" pageEncoding="utf-8"%>
<%@ page import="com.ten.user.Course" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta charset="UTF-8">
	<title>学生选课信息管理系统-显示已选课程</title>
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
<div class="top">
	<div class="title"><p> 学生选课信息管理系统 </p></div>
</div>
<div class="main">
	<div class="main_left">
		<div class="main_left_class00"><img src="img/touxiang.png" class="xwcms" onmouseover="this.src='img/touxiang.png'"
             onmouseout="this.src='img/touxiang.png'" /></div>
		<div class="main_left_class01"><a class="herf" href="stu/choiceCou.jsp"style='color:white'>学生选课</a></div>
		<div class="main_left_class02"><a class="herf" href="stu/showCou.jsp"style='color:white'>课程查询</a></div>
		<div class="main_left_class03"><a class="herf" href="stu/stuInfo.jsp"style='color:white'>学籍信息</a></div>
		<div class="main_left_class100">
		<input class="reset" type="button" value="注销" onClick="window.location.href=('servlet/serDoLogout')">
		</div>
	</div>
					<%
			    	Student stu = null;
			    	if(session.getAttribute("student")==null){
			    		response.sendRedirect("Login.jsp");
			    	}else{
			    		stu = (Student)session.getAttribute("student");%>
	<div class="main_right">
		<div class="info">
			<p>亲爱的<%= stu.getSname() %>同学，下午好</p>
		</div>
		<div class="box">
			<div class="function"><p>[通知]选课系统已开放</p></div>
			<div class="form">
				<table>
  					<tr><td></td></tr>
  					<tr>
						<td>课程编号</td> <td>教学班编号</td> <td>课程名称</td> <td>授课教师</td> <td>职称</td> <td>开课时间</td> <td>开课地点</td> <td>课程学分</td> <td>取消选课</td>
  						
  					</tr>
			  		<%
			   			DaoSc select = new DaoSc();
			   			Iterator<Course> list = select.selectSc(stu.getSNo());
			   			Course cou = null;
			   			while(list.hasNext()){
			   				cou = list.next();
			   		%>
  					<tr>
						<td><%= cou.getCNo() %></td>
						<td><%= cou.getLessonNo() %></td>
						<td><%= cou.getCname() %></td>
						<td><%= cou.getCteacher() %></td>
						<td><%= cou.getTitle() %></td>
						<td><%= cou.getCtime() %></td>
						<td><%= cou.getClassroom() %></td>
						<td><%= cou.getCcredit() %></td>
  					<td><input class="cancle" type="button" value="取消" onClick="window.location.href=('servlet/serDeleteSc?lessonNo=<%= cou.getLessonNo() %>')"></td>
  					</tr>
		  				<%
		   			}
		   		}
		    %>
  				</table>
				
			</div>
		</div>
	</div>
</div>
</body>
</html>
