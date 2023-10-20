package com.ten.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ten.conn.Conn;
import com.ten.user.Teacher;

public class DaoTea {
	
	/**
	 * ½ÌÊ¦µÇÂ¼
	 * **/
	public Teacher loginTea(Teacher tea){
		Teacher tea1 = null;
		try{
			String sql_loginT="select * from teacher t join tuser tu on t.TNo = tu.TNo where t.TNo=? and tu.password=?";
			Connection conn = new Conn().getConn();
			PreparedStatement pst = conn.prepareStatement(sql_loginT);
			pst.setInt(1, tea.getTNo());
			pst.setString(2,tea.getTpassword());
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				tea1 = new Teacher(rs.getInt("TNo"),rs.getInt("FNO"),rs.getString("Tname"),
						rs.getString("GENDER"),rs.getString("nativeplace"),rs.getString("title"),rs.getString("password"));
			}
		}catch(Exception e){e.printStackTrace();}
		return tea1;
	}

}
