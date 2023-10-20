package com.ten.ser.sc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ten.dao.DaoCou;
import com.ten.dao.DaoSc;
import com.ten.dao.DaoStu;
import com.ten.user.Student;
import com.ten.user.SC;
import com.ten.user.Teacher;

public class serInsertSc extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public serInsertSc() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
/******************************************************************************/		
		HttpSession session = request.getSession(); 
		PrintWriter out = response.getWriter();
		Student stu = null;
		if(session.getAttribute("student")==null){
			response.sendRedirect("../Login.jsp");
		}else{
			stu = (Student) session.getAttribute("student");
			out.println(stu.getSNo()+"SNO");
		
/******************************************************************************/
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		try{
			String choiceR[] = request.getParameterValues("choiceCou");
			int sno = stu.getSNo();
			if(choiceR.length!=0){
				SC sc[] = new SC[choiceR.length];
				for(int i =0;i<choiceR.length;i++){
					sc[i] = new SC();
					sc[i].setSNo(sno);
					sc[i].setLessonNo(Integer.parseInt(choiceR[i]));
				}

				if(true){
					DaoSc insert = new DaoSc();
					int rs[] = insert.insertSc(sc);
					int sum =0;
					for(int i =0;i<rs.length;i++){
						if(rs[i]==0) System.out.println("rs"+i+"is null  !!");
						sum += rs[i];
					}
					if(sum!=0) {
						out.println("<h2><center>ѡ�γɹ�</cneter></h2>");
						response.sendRedirect("../stu/showCou.jsp");
					}
				}else {
					out.println("<h2><center>��ѡ��Ŀγ̳�������"+Student.MAXscore+"�֣�</cneter></h2>");
					response.setHeader("refresh", "2;url=../stu/choiceCou.jsp");
				}
			}else {
				out.println("<h2><center>ѡ��ʧ��</cneter></h2>");
				response.setHeader("refresh", "2;url=../stu/choiceCou.jsp");
			}
		}catch(Exception e){e.printStackTrace();out.println("ѡ��ʧ��!");response.setHeader("refresh", "2;url=../stu/choiceCou.jsp");}
		out.println("  </BODY>");
		out.println("</HTML>");}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
