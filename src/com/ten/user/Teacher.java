package com.ten.user;

public class Teacher {
	private int TNo;
	private int FNo;
	private String Tname;
	private String Tpassword;
	private String gender;
	private String nativeplace;
	private String title;

	public Teacher(int TNo, int FNo, String tname, String tpassword, String gender, String nativeplace, String title) {
		this.TNo = TNo;
		this.FNo = FNo;
		Tname = tname;
		Tpassword = tpassword;
		this.gender = gender;
		this.nativeplace = nativeplace;
		this.title = title;
	}

	public Teacher(int tNo, String tname, String tpassword) {
		TNo = tNo;
		Tname = tname;
		Tpassword = tpassword;
	}
	public Teacher( String tname, String tpassword) {
		Tname = tname;
		Tpassword = tpassword;
	}

	public Teacher() {
	}
	public int getTNo() {
		return TNo;
	}

	public void setTNo(int TNo) {
		this.TNo = TNo;
	}

	public int getFNo() {
		return FNo;
	}

	public void setFNo(int FNo) {
		this.FNo = FNo;
	}

	public String getTname() {
		return Tname;
	}

	public void setTname(String tname) {
		Tname = tname;
	}

	public String getTpassword() {
		return Tpassword;
	}

	public void setTpassword(String tpassword) {
		Tpassword = tpassword;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNativeplace() {
		return nativeplace;
	}

	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
