package com.ten.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ten.conn.Conn;
import com.ten.user.Course;
import com.ten.user.Student;

public class DaoStu {
	/**
	 * ѧ����¼
	 * **/
	public Student loginStu(Student stu){
		Student stu1 = null;
		String sql_loginS="select * from student s join suser su on s.SNo = su.SNo where s.SNo=? and su.password=?";
		try{
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_loginS);
			pst.setInt(1, stu.getSNo());
			pst.setString(2,stu.getSpassword());
			ResultSet rs = pst.executeQuery();
			System.out.println("ready login");
			if(rs.next()){
				stu1 = new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(9),0);
				System.out.println("student login select over");
			}
			else
			{
				System.out.println("student login select error");
			}
		}catch(Exception e){e.printStackTrace();}
		return stu1;
	}
	/*
	 * ѧ������ѡ��
	 */
	public Iterator<Course> selectSC(){
		List<Course> list = null;
		Iterator<Course> listall = null;
		String sql_selectsc="select * from sc,course where Sname=? and Spassword=?;";
		try{
			list = new ArrayList<Course>();
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_selectsc);
			pst.setString(1, null);

			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				Course cou = new Course();
			}
		}catch(Exception e){e.printStackTrace();}
		return listall;
	}

	/*
	 * ��ѯ����ѧ��
	 * */
	public List<Student> selectStu(){
		List<Student> list = null;
		try{
			String sql_select = "select s.sno,sname,password,classname,gender from student s join suser su on s.SNo = su.SNo join class c on s.classno = c.classno";//��ѯȫ��ѧ����Ϣ+ѧ��ѧ��;//��ѯ���е�sql���
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_select);
			ResultSet rs = pst.executeQuery();
			list = new ArrayList<Student>();
			DaoCou scredit = new DaoCou();
			while(rs.next()){				
				Student stu = new Student(rs.getInt("sno"),rs.getString("sname"),rs.getString("password"),rs.getString("classname")
						,rs.getString("gender"), scredit.selectScre(rs.getInt("sno")));
				list.add(stu);
			}
		}catch(Exception e){e.printStackTrace();}
		return list;
	}
	/*
	 * ��ѯѧ�����������ѧ��
	 * */
	public Iterator<Student> selectSum(){
		List<Student> list = null;
		Iterator<Student> listall = null;
		try{
			String sql_select = "select student.sno as SNo,Sname,Spassword,Sclass,Ssex,sum(course.ccredit) as Scredit from student,sc,course" +
								"	where student.sno=sc.sno" +
								"		and sc.cno=course.cno" +
								"		 group by student.sno";//��ѯȫ��ѧ����Ϣ+ѧ��ѧ��ɸѡ����ѧ�ֲ����
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_select);
			ResultSet rs = pst.executeQuery();
			list = new ArrayList<Student>();
			while(rs.next()){				
				Student stu = new Student(rs.getInt("SNo"),rs.getString("Sname"),rs.getString("Spassword"),rs.getString("Sclass")
						,rs.getString("Ssex"),rs.getFloat("Scredit"));
				list.add(stu);
			}
			listall = list.iterator();
		}catch(Exception e){e.printStackTrace();}
		return listall;
	}
	/*
	 * ��ѯѧ����ѧ��_ĳλѧ��
	 * */
	public Student selectScre(Student stu){
		ResultSet rs = null;
		try{
			String sql_insert = "select student.sno as sno,sum(course.ccredit) as scredit from student,sc,course" +
			"	where student.sno=sc.sno" +
			"		and sc.cno=course.cno" +
			"		 and student.sno=?";//��ѯĳλͬѧѧ��
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_insert);
			pst.setInt(1,stu.getSNo());
			rs = pst.executeQuery();
			if(rs.next()){
				System.out.println("Student_id"+stu.getSNo()+"select one over!");
				stu = new Student(rs.getInt("sno"),null,null,null,null,rs.getFloat("scredit"));	
			}
		}catch(Exception e){e.printStackTrace();}
		return stu;
	}
	/*
	 * ��ѯĳ����ѧ��
	 * */
	public Student selectStu(int id){
		Student Stu = null;
		try{
			String sql_selectone = "select * from student s left join suser su on s.SNO = su.SNO left join class c on c.classno = s.classno where s.SNO =?";//��ѯĳһ��ѧ����Ϣ
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_selectone);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				Stu = new Student(rs.getInt("SNo"),rs.getString("Sname"),rs.getString("password"),rs.getString("classname")
						,rs.getString("GENDER"),0);
			}
		}catch(Exception e){e.printStackTrace();}
		return Stu;
	}
	/*
	 * ��ѯĳ��ѧ����Ϣ��ģ����ѯ��
	 * */
	public Iterator<Student> selectStu(String aa){//aa��Ϊģ����ѯ�Ĺؼ���
		List<Student> list = null;
		Iterator<Student> listall = null;
		try{
			String sql_selectfuz = "select * from student where CNo=? or....";//fuzzy queryģ����ѯ
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_selectfuz);
			ResultSet rs = pst.executeQuery();
			list = new ArrayList<Student>();
			while(rs.next()){
				Student stu = new Student(rs.getInt("SNo"),rs.getString("Sname"),rs.getString("Spassword"),rs.getString("Sclass")
						,rs.getString("Ssex"),0);
				list.add(stu);
			}
			listall = list.iterator();
		}catch(Exception e){e.printStackTrace();}
		return listall;
	}
	/*
	 * �޸�ĳ��ѧ����Ϣ
	 * */
	public int updateStu(Student stu){
		int rs = 0;
		try{
			String sql_update = "update Suser set password=? where SNo=?";//�޸�һ��ѧ����Ϣ
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_update);
			pst.setString(1, stu.getSpassword());
			pst.setInt(2, stu.getSNo());
			rs = pst.executeUpdate();
			if(rs!=0){
				System.out.println("Student_id"+stu.getSNo()+"update over!");
			}
		}catch(Exception e){e.printStackTrace();}
		return rs;
	}
	/*
	 * �޸�ĳ��ѧ��ѧ������
	 * */
	public int updateStuPsd(Student stu){
		int rs = 0;
		try{
			String sql_update = "update Suser set password=? where SNo=?";//�޸�һ��ѧ����Ϣ
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_update);
			pst.setString(1, stu.getSpassword());
			pst.setInt(2, stu.getSNo());
			rs = pst.executeUpdate();
			if(rs!=0){
				System.out.println("Student_id"+stu.getSNo()+"update over!");
			}
		}catch(Exception e){e.printStackTrace();}
		return rs;
	}
	/*
	 * ɾ��ĳ��ѧ����Ϣ
	 * */
	public int deleteStu(int id){
		int rs = 0;
		try{
			String sql_delete = "delete from suser where SNo=?";//ɾ��һ���γ���Ϣ
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_delete);
			pst.setInt(1, id);
			rs = pst.executeUpdate();
			if(rs!=0){
				System.out.println("Student_id"+id+"delete over!");
			}
		}catch(Exception e){e.printStackTrace();}
		return rs;
	}
	/*
	 * ���һλѧ����Ϣ
	 * */
	public int insertStu(Student stu){
		int rs = 0;
		try{
			String sql_insert = "insert into student(Sname,Spassword,Sclass,Ssex) values(?,?,?,?)";//���һ���γ���Ϣ
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_insert);
			pst.setString(1,stu.getSname());
			pst.setString(2, stu.getSpassword());
			pst.setString(3, stu.getSclass());
			pst.setString(4, stu.getSsex());
			rs = pst.executeUpdate();
			if(rs!=0){
				System.out.println("Student_id"+stu.getSNo()+"insert over!");
			}
		}catch(Exception e){e.printStackTrace();}
		return rs;
	}
	
	
}


