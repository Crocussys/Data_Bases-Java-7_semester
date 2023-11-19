package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentsDb {
    private Connection con;
    private StudentDao dao;
    public StudentsDb(){
        dao = new StudentDao();
    }
    public StudentsDb getInstance(){
        return this;
    }
    public void connect(String url) throws SQLException {
        con = DriverManager.getConnection(url);
        dao.setConnection(con);
    }
    public StudentDao getStudentDao(){
        return dao;
    }
}
