<%@ page language="java" import="java.util.*,com.ten.user.*,com.ten.dao.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
	<title>学生选课信息管理系统-课程信息</title>
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
	<div class="title"><p> 学生选课信息管理系统</p></div>
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
					<%
	  	Teacher tea = null;
	  	if(session.getAttribute("teacher")==null){
	  		response.sendRedirect("/WebContent/Login.jsp");
	  	}else{
	  		tea = (Teacher)session.getAttribute("teacher");
	   %>
	<div class="main_right">
		<div class="info">
			<p>亲爱的<%= tea.getTname() %>老师，下午好</p>
		</div>
		<div class="box">
			<div class="function"><p>[通知]选课系统已开放</p></div>
			<%
    		DaoCou select = new DaoCou();
			Iterator<Course> list = select.selectCou(); %>

			<div class="form">
				<table>
					<td><input class="btn_0" type="button" value="添加课程" onclick="window.location.href=('/WebContent/tea/InsertCou.jsp')"/></td>
  					<tr><td></td></tr>
					<td>课程编号</td><td>课程名称</td><td>课程学分</td><td>开课地点</td><td>开课时间</td><td>教学班编号</td><td>修改教学班</td><td>删除教学班</td>
  					</tr>
			  		<%
			   			while(list.hasNext()){
						Course cou = list.next();
			   		%>
  					<tr>
  					<td><%= cou.getCNo() %></td>
  					<td><%= cou.getCname() %></td>
  					<td><%= cou.getCcredit() %></td>
						<td><%= cou.getClassroom()==null ? "暂无" : cou.getClassroom()%></td>
						<td><%= cou.getCtime()==null ? "暂无" : cou.getCtime()%></td>
					<td><%= cou.getLessonNo()==null ? "暂无" : cou.getLessonNo()%></td>
						<%
							if (cou.getLessonNo() != null) {
						%>
  					<td><input  class="btn" type="button" value="修改教学班" onClick="window.location.href=('/WebContent/tea/updateCou.jsp?cid=<%= cou.getCNo() %>&lid=<%= cou.getLessonNo()%>')"></td>
<%--						<td><input  class="btn" type="button" value="删除教学班" onClick="window.location.href=('/WebContent/servlet/serDeleteCou?cid=<%= cou.getCNo() %>&lid=<%= cou.getLessonNo()%>')"></td>--%>
  					<td><input  class="btn" type="button" value="删除教学班" onClick="confirmDelete('<%= cou.getCNo() %>','<%= cou.getLessonNo() %>')">
						<script>
							function confirmDelete(courseId, lessonId) {
								console.log("confirmDelete被调用");
								if (confirm("确定要删除这个教学班吗？")) {
									// 如果用户点击了确定按钮，则执行删除操作
									window.location.href = '/WebContent/servlet/serDeleteCou?cid='+courseId+'&lid='+lessonId;
								} else {
									// 如果用户点击了取消按钮，不执行删除操作
								}
							}
						</script>
					</td>

						<%
							}
							else {
						%>
						<td><input class="btn" type="button" value="添加教学班" onclick="window.location.href=('/WebContent/tea/InsertLesson.jsp?cid=<%= cou.getCNo() %>')"/></td>
						<td><input class="btn" type="button" value="删除课程" onclick="confirmDeleteCourse('<%= cou.getCNo() %>')">

							<script>
								function confirmDeleteCourse(courseId) {
									console.log("confirmDeleteCourse被调用");
									if (confirm("确定要删除这门课程吗？")) {
										// 如果用户点击了确定按钮，则执行删除操作
										window.location.href = '/WebContent/servlet/serDeleteLesson?cid='+courseId;
									} else {
										// 如果用户点击了取消按钮，不执行删除操作
									}
								}
							</script>

						</td>


						<%
							}
		   			}
		   		}
		    %>
  				</table>

				<script>
					// 解析 URL 中的参数
					function getParameterByName(name, url) {
						if (!url) url = window.location.href;
						name = name.replace(/[\[\]]/g, "\\$&");
						var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
								results = regex.exec(url);
						if (!results) return null;
						if (!results[2]) return '';
						return decodeURIComponent(results[2].replace(/\+/g, " "));
					}

					// 获取 URL 中的 message 参数值
					var messageParam = getParameterByName("message");

					// 根据参数值显示提示信息
					if (messageParam) {
						alert(messageParam);
					}
				</script>

			</div>
		</>
	</div>
</div>
</body>
</html>
