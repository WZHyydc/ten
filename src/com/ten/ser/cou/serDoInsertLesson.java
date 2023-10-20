package com.ten.ser.cou;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ten.dao.DaoCou;
import com.ten.user.Course;

public class serDoInsertLesson extends HttpServlet {

    /**
     * Constructor of the object.
     */
    public serDoInsertLesson() {
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
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY><center>");
        try{
            request.setCharacterEncoding("utf-8");
            Course cou = new Course(request.getParameter("CNo"),request.getParameter("Lessonno"),request.getParameter("Cname"),
                    request.getParameter("Cteacher"),
                    request.getParameter("Title"),request.getParameter("Classtime"),request.getParameter("Classroom"),
                    request.getParameter("Ccredit"),request.getParameter("Maxnum"));

            DaoCou insert = new DaoCou();
            int rs = insert.insertLesson(cou);
            String message;
            if(rs!=0){
                System.out.println("insert lesson susses!:"+cou.getCNo());
                 message = "insert lesson susses!:";

            }else{
                System.out.println("insert lesson error!:"+cou.getCNo());
                message = "insert lesson error!:";
            }
            response.sendRedirect("/WebContent/tea/selectCou.jsp?message=" + URLEncoder.encode(message));

        }catch(Exception e){e.printStackTrace();}
        out.println("  </center></BODY>");
        out.println("</HTML>");
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
