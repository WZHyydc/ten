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

public class DaoCou {
	
	public static final String sql_select_lesson_course = "select * from course c left join lesson l on c.CNO = l.CNO left join teacher t on t.TNO = l.TNO";//��ѯ���еĿγ̣�����û�п����ѧ��Ŀγ�
	public static final String sql_select_lesson ="select * from course c left join lesson l on c.CNO = l.CNO left join teacher t on t.TNO = l.TNO where l.LESSONNO is not null";
	public static final String sql_selectfuz = "select * from course where CNo=? or....";//fuzzy queryģ����ѯ
	public static final String sql_selectone_lesson = "select * from course c left join lesson l on c.CNO = l.CNO left join teacher t on t.TNO = l.TNO " +
			"where c.CNO = ? and l.lessonno = ?";//��ѯĳһ����ѧ��Ŀγ���Ϣ

	public static final String sql_selectone_coursewithoutLeson = "select * from course c left join lesson l on c.CNO = l.CNO left join teacher t on t.TNO = l.TNO " +
			"where c.CNO = ? and l.lessonno is null";//��ѯĳһ��û��ָ����ѧ��Ŀγ���Ϣ
	public static final String sql_update_course = "update course set Cname=?,Ccredit=? where CNo=?";//�޸�һ���γ���Ϣ

	public static final String sql_update_lesson = "update lesson set Classtime=?,Classroom=?,Maxnum=? where LessonNo=? and Cno=?";//�޸�һ���γ���Ϣ
	public static final String sql_delete_course = "delete from course where Cno=?";//ɾ��һ���γ���Ϣ
	public static final String sql_delete_lesson = "delete from lesson where Cno=? and LESSONNO=?";//ɾ��һ����ѧ����Ϣ

	public static final String sql_insert_course = "insert into course(CNO,Cname,Ccredit) values(?,?,?)";//���һ���γ���Ϣ

	public static final String sql_insert_lesson ="{call P_ADDLESSON(?,?,?,?,?,?)}";
	public static final String sql_select_oneteacher = "select * from teacher where tname = ?";
	/*
	 * ��ѯ���пγ���Ϣ
	 * */
	public Iterator<Course> selectCou(){
		List<Course> list = null;
		Iterator<Course> listall = null;
		try{
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_select_lesson_course);
			ResultSet rs = pst.executeQuery();
			list = new ArrayList<Course>();
			while(rs.next()){				
				Course cou = new Course(rs.getString("CNO"),rs.getString("LESSONNO"),rs.getString("CNAME"),
						rs.getString("TNAME"),rs.getString("TITLE"), rs.getString("CLASSTIME"),
						rs.getString("CLASSROOM"),rs.getString("CCREDIT")
						);
				list.add(cou);
			}
			listall = list.iterator();
		}catch(Exception e){e.printStackTrace();}
		return listall;
	}

	public Iterator<Course> selectLesson(){
		List<Course> list = null;
		Iterator<Course> listall = null;
		try{
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_select_lesson);
			ResultSet rs = pst.executeQuery();
			list = new ArrayList<Course>();
			while(rs.next()){
				Course cou = new Course(rs.getString("CNO"),rs.getString("LESSONNO"),rs.getString("CNAME"),
						rs.getString("TNAME"),rs.getString("TITLE"), rs.getString("CLASSTIME"),
						rs.getString("CLASSROOM"),rs.getString("CCREDIT")
				);
				list.add(cou);
			}
			listall = list.iterator();
		}catch(Exception e){e.printStackTrace();}
		return listall;
	}
	/*
	 * ��ѯĳ���γ���Ϣ
	 * */
	public Course selectCou(String cid , int lid){
		Course cou = null;
		try{
			Connection conn = new Conn().getConn();

			System.out.println("cid:"+cid);

			PreparedStatement pst = conn.prepareStatement(sql_selectone_coursewithoutLeson);
			pst.setString(1, cid);
			ResultSet rs = pst.executeQuery();

			System.out.println("rs:"+rs);

			if(rs.next()){
				cou = new Course(rs.getString("CNO"),rs.getString("LESSONNO"),rs.getString("CNAME"),
						rs.getString("TNAME"),rs.getString("TITLE"), rs.getString("CLASSTIME"),
						rs.getString("CLASSROOM"),rs.getString("CCREDIT"));
			}
		}catch(Exception e){e.printStackTrace();}
		return cou;
	}

	public Course selectCou(String cid, String  lid){
		Course cou = null;
		try{
			Connection conn = new Conn().getConn();

			System.out.println("cid:"+cid);
			System.out.println("lid:"+lid);
			int lid1;
			try {
				lid1 = Integer.parseInt(lid);
			}
			catch (NumberFormatException e) {
				lid1 = 0;
				System.out.println("lid is not a number");
			}
			PreparedStatement pst = conn.prepareStatement(sql_selectone_lesson);
			pst.setString(1, cid);
			pst.setInt(2, lid1);
			ResultSet rs = pst.executeQuery();
			//test2
			System.out.println("rs:"+rs);

			if(rs.next()){
				cou = new Course(rs.getString("CNO"),rs.getString("LESSONNO"),rs.getString("CNAME"),
						rs.getString("TNAME"),rs.getString("TITLE"), rs.getString("CLASSTIME"),
						rs.getString("CLASSROOM"),rs.getString("CCREDIT"),rs.getString("MAXNUM"));
			}
		}catch(Exception e){e.printStackTrace();}
		return cou;
	}
	/*
	 * ��ѯĳ���γ���Ϣ��ģ����ѯ��
	 * */
	public Iterator<Course> selectCou(String aa){//aa��Ϊģ����ѯ�Ĺؼ���
		List<Course> list = null;
		Iterator<Course> listall = null;
		try{
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_selectone_lesson);
			ResultSet rs = pst.executeQuery();
			list = new ArrayList<Course>();
			while(rs.next()){
				Course cou = new Course(rs.getString("CNO"),rs.getString("LESSONNO"),rs.getString("CNAME"),
						rs.getString("TNAME"),rs.getString("TITLE"), rs.getString("CLASSTIME"),
						rs.getString("CLASSROOM"),rs.getString("CCREDIT"));
				list.add(cou);
			}
			listall = list.iterator();
		}catch(Exception e){e.printStackTrace();}
		return listall;
	}
	/*
	 * �޸Ŀγ���Ϣ
	 * */
	public int updateCou(Course cou){
		int rs = 0;
		try{
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_update_course);
			pst.setString(1, cou.getCname());
			pst.setString(2, cou.getCcredit());
			pst.setString(3, cou.getCNo());
			rs = pst.executeUpdate();
			if(rs!=0){
				System.out.println("course_id"+cou.getCNo()+"update over!");
			}
		}catch(Exception e){e.printStackTrace();}
		return rs;
	}

	/*
	 * �޸Ľ�ѧ����Ϣ
	 * */


		public int updateLesson(Course cou){
		int rs = 0;
		try{
			Connection conn = new Conn().getConn();
			//�鿴cou����Ϣ
			System.out.println("cou.getCtime():"+cou.getCtime());
			System.out.println("cou.getClassroom():"+cou.getClassroom());
			System.out.println("cou.getMaxnum():"+Integer.parseInt(cou.getMaxnum()));
			System.out.println("cou.getLessonNo():"+cou.getLessonNo());
			System.out.println("cou.getCNo():"+cou.getCNo());

			PreparedStatement pst = conn.prepareStatement(sql_update_lesson);
			pst.setString(1, cou.getCtime());
			pst.setString(2, cou.getClassroom());
			pst.setInt(3, Integer.parseInt(cou.getMaxnum()));
			pst.setInt(4, Integer.parseInt(cou.getLessonNo()));
			pst.setString(5, cou.getCNo());
			rs = pst.executeUpdate();
			if(rs!=0){
				System.out.println("course_id"+cou.getCNo()+"lessonid"+cou.getLessonNo()+"update over!");
			}
		}catch(Exception e){e.printStackTrace();}
		return rs;
	}
	/*
	 * ɾ��ĳ���γ���Ϣ
	 * */
	public int deleteCou(String cid){
		int rs = 0;
		try{
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_delete_course);
			pst.setString(1, cid);
			rs = pst.executeUpdate();
			if(rs!=0){
				System.out.println("course_id"+cid+"delete over!");
			}
		}catch(Exception e){e.printStackTrace();}
		return rs;
	}

	public int deleteLesson(String cid, String lid){
		int rs = 0;
		try{
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_delete_lesson);

			int lid1;
			try {
				lid1 = Integer.parseInt(lid);
			}
			catch (NumberFormatException e)
			{
				lid1 = 0;
				System.out.println("lid is not a number");
			}

			pst.setString(1, cid);
			pst.setInt(2, lid1);


			rs = pst.executeUpdate();
			if(rs!=0){
				System.out.println("course_id"+cid+"lesson_id"+lid+"delete over!");
			}
		}catch(Exception e){e.printStackTrace();}
		return rs;
	}
	/*
	 * ���һ���γ���Ϣ
	 * */
	public int insertCou(Course cou){
		int rs = 0;
		try{
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_insert_course);
			pst.setString(1, cou.getCNo());
			pst.setString(2,cou.getCname());
			pst.setFloat(3, Float.parseFloat(cou.getCcredit()));
			rs = pst.executeUpdate();
			if(rs!=0){
				System.out.println("course_name:"+cou.getCname()+"insert over!");
			}
		}catch(Exception e){e.printStackTrace();}
		return rs;
	}

	public int insertLesson(Course cou){
		int rs = 0;
		try{
			Connection conn = new Conn().getConn();
			PreparedStatement pst_before = conn.prepareStatement(sql_select_oneteacher);
			pst_before.setString(1,cou.getCteacher());
			ResultSet rs_0 = pst_before.executeQuery();
			if(rs_0.next())
			{
				int TNO = rs_0.getInt("TNO");
				System.out.println("insert lesson teacher finded!");
				CallableStatement cst =conn.prepareCall(sql_insert_lesson);
				cst.setInt(1,Integer.parseInt(cou.getLessonNo()));
				cst.setInt(2,TNO);
				cst.setString(3,cou.getCNo());
				cst.setString(4,cou.getClassroom());
				cst.setString(5,cou.getCtime());
				cst.setInt(6,Integer.parseInt(cou.getMaxnum()));
				rs = cst.execute() ? 0 :1 ;
			}
			else
			{
				System.out.println("insert lesson teacher not finded!");
			}

			if(rs!=0){
				System.out.println("lesson_no:"+cou.getLessonNo()+"insert over!");
			}
		}catch(Exception e){e.printStackTrace();}
		return rs;
	}

	/*
	 * ����ѧ�ţ���ѯѧ����ѧ��
	 * */
	public float selectScre(int sno){
		float sum = 0;
		try{
			String sql = "select s.sno,sum(ccredit) as scredit from" +
					" (student s join sc on s.sno = sc.sno join lesson l on sc.lessonno = l.lessonno join course c on l.cno = c.cno)  " +
					"where s.sno = ? group by s.sno";
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, sno);
			ResultSet rs = null;
			rs = pst.executeQuery();
			if(rs.next()){
				sum = rs.getFloat("scredit");
			}
		}catch(Exception e){e.printStackTrace();}
		return sum;
	}
}
