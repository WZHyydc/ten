package com.ten.user;

public class Course {
    private String CNo;
    private String LessonNo;
    private String Cname;
    private String Cteacher;
    private String Title;
    private String Ctime;
    private String Classroom;
    private String Ccredit;

    private String Maxnum;

    // 无参构造函数
    public Course() {
    }

    /**
     * @param CNo
     * @param LessonNo
     * @param Cname
     * @param Cteacher
     * @param Title
     * @param Ctime
     * @param Classroom
     * @param Ccredit
     */
    // 构造函数接受所有成员变量
    public Course(String CNo, String LessonNo, String Cname, String Cteacher, String Title, String Ctime, String Classroom, String Ccredit) {
        this.CNo = CNo;
        this.LessonNo = LessonNo;
        this.Cname = Cname;
        this.Cteacher = Cteacher;
        this.Title = Title;
        this.Ctime = Ctime;
        this.Classroom = Classroom;
        this.Ccredit = Ccredit;
    }

    // 构造函数接受部分成员变量
    public Course(String CNo, String lessonNo, String cname, String cteacher, String title, String ctime, String classroom) {
        this.CNo = CNo;
        LessonNo = lessonNo;
        Cname = cname;
        Cteacher = cteacher;
        Title = title;
        Ctime = ctime;
        Classroom = classroom;
    }

    public Course(String CNo, String lessonNo, String cname, String cteacher, String title, String ctime, String classroom, String ccredit, String maxnum) {
        this.CNo = CNo;
        LessonNo = lessonNo;
        Cname = cname;
        Cteacher = cteacher;
        Title = title;
        Ctime = ctime;
        Classroom = classroom;
        Ccredit = ccredit;
        Maxnum = maxnum;
    }
    // Getter 和 Setter 方法
    public String getCNo() {
        return CNo;
    }

    public void setCNo(String CNo) {
        this.CNo = CNo;
    }

    public String getLessonNo() {
        return LessonNo;
    }

    public void setLessonNo(String LessonNo) {
        this.LessonNo = LessonNo;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String Cname) {
        this.Cname = Cname;
    }

    public String getCteacher() {
        return Cteacher;
    }

    public void setCteacher(String Cteacher) {
        this.Cteacher = Cteacher;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getCtime() {
        return Ctime;
    }

    public void setCtime(String Ctime) {
        this.Ctime = Ctime;
    }

    public String getClassroom() {
        return Classroom;
    }

    public void setClassroom(String Classroom) {
        this.Classroom = Classroom;
    }

    public String getCcredit() {
        return Ccredit;
    }

    public void setCcredit(String Ccredit) {
        this.Ccredit = Ccredit;
    }

    public String getMaxnum() {
        return Maxnum;
    }

    public void setMaxnum(String Maxnum) {
        this.Maxnum = Maxnum;
    }

}
