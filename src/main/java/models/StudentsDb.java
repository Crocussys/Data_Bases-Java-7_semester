package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentsDb {
    private final StudentDao student_dao;
    private final SubjectDao subject_dao;
    public StudentsDb(){
        student_dao = new StudentDao();
        subject_dao = new SubjectDao();
    }
    public StudentsDb getInstance(){
        return this;
    }
    public void connect(String url) throws SQLException {
        Connection con = DriverManager.getConnection(url);
        student_dao.setConnection(con);
        subject_dao.setConnection(con);
    }
    public StudentDao getStudentDao(){
        return student_dao;
    }
    public SubjectDao getSubjectDao(){
        return subject_dao;
    }
}
