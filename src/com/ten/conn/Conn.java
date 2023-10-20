package com.ten.conn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.String;
import java.sql.Connection;
import java.sql.DriverManager;


public class Conn {
	Connection conn = null;
	public String driver;
	public String url;
	public String user;
	public String pass;
	public Connection getConntest()
	{
        String path = "src/com/ten/conn/config.txt";
		String[] config = new String[0];
		try {
			config = new BufferedReader(new FileReader(path)).lines().toArray(String[]::new);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		this.driver = config[0];
		this.url = config[1];
		this.user = config[2];
		this.pass = config[3];
		try{
			Class.forName(this.driver);
			conn = DriverManager.getConnection(this.url,this.user,this.pass);
		}catch(Exception e){e.printStackTrace();}
		return conn;
	}

	public static final String DBDRIVER="oracle.jdbc.OracleDriver";
	public static final String DBURL="jdbc:oracle:thin:@172.24.1.41:1521:FREE";
	public static final String DBUSER="system";
	public static final String DBPASS="system";

	public Connection getConn()
	{
		try{
			Class.forName(DBDRIVER);
			conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
		}catch(Exception e){e.printStackTrace();}
		return conn;
	}
}
