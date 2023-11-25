package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private Connection con;
    public void setConnection(Connection con){
        this.con = con;
    }
    private Student serializer(int id, String name, String address, String grp, int age){
        Student model = new Student();
        model.id.setValue(id);
        model.name.setValue(name);
        model.address.setValue(address);
        model.grp.setValue(grp);
        model.age.setValue(age);
        return model;
    }
    public void createTable() throws SQLException {
        Statement st = con.createStatement();
        st.execute("CREATE TABLE STUDENT(ID INT NOT NULL AUTO_INCREMENT, NAME VARCHAR(255) NOT NULL, GRP VARCHAR(31), ADDRESS VARCHAR(255), AGE INT, PRIMARY KEY(ID))");
    }
    public void insertStudent(Student model) throws SQLException {
        PreparedStatement st = con.prepareStatement("INSERT INTO STUDENT (NAME, ADDRESS, GRP, AGE) VALUES (?,?,?,?)");
        st.setString(1, model.name.getValue());
        st.setString(2, model.address.getValue());
        st.setString(3, model.grp.getValue());
        st.setInt(4, model.age.getValue());
        st.execute();
    }
    public Student getStudent(int id) throws SQLException {
        PreparedStatement st = con.prepareStatement("SELECT * FROM STUDENT WHERE ID=?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        return serializer(Integer.parseInt(rs.getString("ID")), rs.getString("NAME"),
                rs.getString("ADDRESS"), rs.getString("GRP"), Integer.parseInt(rs.getString("AGE")));
    }
    public void deleteStudent(int id) throws SQLException {
        PreparedStatement st = con.prepareStatement("DELETE FROM STUDENT WHERE ID=?");
        st.setInt(1, id);
        st.execute();
    }
    public List<Student> getStudents(int ageMin, int ageMax) throws SQLException {
        PreparedStatement st = con.prepareStatement("SELECT * FROM STUDENT WHERE (AGE >= ? AND Age <= ?)");
        st.setInt(1, ageMin);
        st.setInt(2, ageMax);
        ResultSet rs = st.executeQuery();
        List<Student> ans = new ArrayList<>();
        while (rs.next()) {
            ans.add(serializer(Integer.parseInt(rs.getString("ID")), rs.getString("NAME"),
                    rs.getString("ADDRESS"), rs.getString("GRP"), Integer.parseInt(rs.getString("AGE"))));
        }
        return ans;
    }
    public List<Student> getStudents(String name) throws SQLException {
        PreparedStatement st = con.prepareStatement("SELECT * FROM STUDENT WHERE NAME=?");
        st.setString(1, name);
        ResultSet rs = st.executeQuery();
        List<Student> ans = new ArrayList<>();
        while (rs.next()) {
            ans.add(serializer(Integer.parseInt(rs.getString("ID")), rs.getString("NAME"),
                    rs.getString("ADDRESS"), rs.getString("GRP"), Integer.parseInt(rs.getString("AGE"))));
        }
        return ans;
    }
    public List<Student> getStudentsByGroup(String grp) throws SQLException {
        PreparedStatement st = con.prepareStatement("SELECT * FROM STUDENT WHERE GRP=?");
        st.setString(1, grp);
        ResultSet rs = st.executeQuery();
        List<Student> ans = new ArrayList<>();
        while (rs.next()) {
            ans.add(serializer(Integer.parseInt(rs.getString("ID")), rs.getString("NAME"),
                    rs.getString("ADDRESS"), rs.getString("GRP"), Integer.parseInt(rs.getString("AGE"))));
        }
        return ans;
    }
}
