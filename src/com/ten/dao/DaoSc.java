package com.ten.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ten.conn.Conn;
import com.ten.user.Course;
import com.ten.user.SC;
//import com.ten.user.SedCou;

public class DaoSc {
	/*
	 * 选课关系插入  	一次插入数条
	 * */
	@SuppressWarnings("null")
	/*
	 * 选课关系插入  	一次插入数条
	 * */

	public int[] insertSc(SC sc[]){
		int[] rs = null;
		try{
			String sql = "{call P_StudentSelectLesson(?,?)}" ;
			Connection conn = new Conn().getConn();
			CallableStatement cst =conn.prepareCall(sql);

			SC tem = null;
			rs = new int[sc.length];
			for(int i = 0;i< sc.length;i++){
				tem = sc[i];
				cst.setInt(1, tem.getSNo());
				cst.setInt(2, tem.getLessonNo());
				rs[i] = cst.execute() ? 0 : 1;
				System.out.println("rs["+i+"]="+rs[i]);
			}

		}catch(Exception e){e.printStackTrace();}
		return rs;
	}



	/*
	 * 选课关系删除  	一次删除一条
	 * */
	public int deleteSc(int sno,int lessonNo){
		int rs = 0;
		try{
			String sql = "delete from sc where SNO=? and LESSONNO=?";
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, sno);
			pst.setInt(2, lessonNo);
			rs = pst.executeUpdate();
		}catch(Exception e){e.printStackTrace();}
		return rs;
	}
	/*
	 * 查询某位学生已选课程
	 * */
	public Iterator<Course> selectSc(int sno){
		Iterator<Course> listall = null;
		Course cou = null;
		try{
			List<Course> list  = new ArrayList<Course>();
			String sql = "select * from SC s, LESSON l , COURSE c , TEACHER t where l.CNO = c.CNO and t.TNO = l.TNO and s.LESSONNO = l.LESSONNO and s.SNO =?";
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, sno);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				cou = new Course();
				cou.setCNo(rs.getString("CNO"));
				cou.setLessonNo(rs.getString("LESSONNO"));
				cou.setCname(rs.getString("CNAME"));
				cou.setClassroom(rs.getString("CNAME"));
				cou.setCteacher(rs.getString("TNAME"));
				cou.setTitle(rs.getString("TITLE"));
				cou.setCtime(rs.getString("CLASSTIME"));
				cou.setClassroom(rs.getString("CLASSROOM"));
				cou.setCcredit(rs.getString("Ccredit"));
				list.add(cou);
				System.out.println("selected course is over!!");
			}
			listall = list.iterator();
		}catch(Exception e){e.printStackTrace();}
		return listall;
	}
	/*
	 * 根据学号、课号唯一查询选课关系
	 * */
	public SC selectone(int sno,int cno){
		ResultSet rs = null;
		SC sc = null;
		try{
			String sql = "select * from sc where sno=? and cno=?";
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,sno);
			pst.setInt(2,cno);
			rs = pst.executeQuery();
			if(rs.next()){
				System.out.println("classroom select over!!");	
				sc = new SC();
				sc.setSNo(rs.getInt("SNo"));
			}
		}catch(Exception e){e.printStackTrace();}
		return sc;
	}
	/*
	 * 选课关系修改教师
	 * */
	public int updateClass(SC sc){
		int rs = 0;
		try{
			String sql = "update sc set classroom=? where sno=? and cno=?";
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql);
			rs = pst.executeUpdate();
			if(rs!=0) System.out.println("classroom update over!!");			
		}catch(Exception e){e.printStackTrace();}
		return rs;
	}
	
}
