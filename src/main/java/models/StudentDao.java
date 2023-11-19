package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private Connection con;
    public void setConnection(Connection con){
        this.con = con;
    }
    public void createTable() throws SQLException {
        Statement st = con.createStatement();
        st.execute("CREATE TABLE STUDENT(ID INT NOT NULL AUTO_INCREMENT, NAME VARCHAR(255) NOT NULL, GRP VARCHAR(31), ADDRESS VARCHAR(255), AGE INT, PRIMARY KEY(ID))");
    }
    public void insertStudent(Student model) throws SQLException {
        Statement st = con.createStatement();
        st.execute("INSERT INTO STUDENT (NAME, ADDRESS, GRP, AGE) VALUES (" + model.name.getValue() + ", " +
                model.address.getValue() + ", " + model.grp.getValue() + ", " + model.age.getValue() + ")");
    }
    public Student getStudent(int id) throws SQLException {
        Student model = new Student();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM STUDENT WHERE ID=" + Integer.toString(id));
        model.id.setValue(Integer.parseInt(rs.getString("ID")));
        model.name.setValue(rs.getString("NAME"));
        model.address.setValue(rs.getString("ADDRESS"));
        model.grp.setValue(rs.getString("GRP"));
        model.age.setValue(Integer.parseInt(rs.getString("AGE")));
        return model;
    }
    public void deleteStudent(int id) throws SQLException {
        Statement st = con.createStatement();
        st.execute("DELETE FROM STUDENT WHERE ID=" + Integer.toString(id));
    }
    public List<Student> getStudents(int ageMin, int ageMax) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM STUDENT WHERE (AGE >= " + Integer.toString(ageMin) +
                " AND Age <= " + Integer.toString(ageMax) + ")");
        List<Student> ans = new ArrayList<Student>();
        while (rs.next()) {
            Student model = new Student();
            model.id.setValue(Integer.parseInt(rs.getString("ID")));
            model.name.setValue(rs.getString("NAME"));
            model.address.setValue(rs.getString("ADDRESS"));
            model.grp.setValue(rs.getString("GRP"));
            model.age.setValue(Integer.parseInt(rs.getString("AGE")));
            ans.add(model);
        }
        return ans;
    }
    public List<Student> getStudents(String name) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM STUDENT WHERE NAME='" + name + "'");
        List<Student> ans = new ArrayList<Student>();
        while (rs.next()) {
            Student model = new Student();
            model.id.setValue(Integer.parseInt(rs.getString("ID")));
            model.name.setValue(rs.getString("NAME"));
            model.address.setValue(rs.getString("ADDRESS"));
            model.grp.setValue(rs.getString("GRP"));
            model.age.setValue(Integer.parseInt(rs.getString("AGE")));
            ans.add(model);
        }
        return ans;
    }
    public List<Student> getStudentsByGroup(String grp) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM STUDENT WHERE GRP='" + grp + "'");
        List<Student> ans = new ArrayList<Student>();
        while (rs.next()) {
            Student model = new Student();
            model.id.setValue(Integer.parseInt(rs.getString("ID")));
            model.name.setValue(rs.getString("NAME"));
            model.address.setValue(rs.getString("ADDRESS"));
            model.grp.setValue(rs.getString("GRP"));
            model.age.setValue(Integer.parseInt(rs.getString("AGE")));
            ans.add(model);
        }
        return ans;
    }
}
