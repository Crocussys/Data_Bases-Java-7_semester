package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao {
    private Connection con;
    public void setConnection(Connection con){
        this.con = con;
    }
    private Subject serializer(int id, String name, String grp, String homework){
        Subject model = new Subject();
        model.id.setValue(id);
        model.name.setValue(name);
        model.grp.setValue(grp);
        model.homework.setValue(homework);
        return model;
    }
    public void createTable() throws SQLException {
        Statement st = con.createStatement();
        st.execute("CREATE TABLE SUBJECT(ID INT NOT NULL AUTO_INCREMENT, NAME VARCHAR(255) NOT NULL, GRP VARCHAR(31), HOMEWORK VARCHAR(511), PRIMARY KEY(ID))");
    }
    public void insertSubject(Subject model) throws SQLException {
        PreparedStatement st = con.prepareStatement("INSERT INTO SUBJECT (NAME, GRP, HOMEWORK) VALUES (?,?,?)");
        st.setString(1, model.name.getValue());
        st.setString(2, model.grp.getValue());
        st.setString(3, model.homework.getValue());
        st.execute();
    }
    public Subject getSubject(int id) throws SQLException {
        PreparedStatement st = con.prepareStatement("SELECT * FROM SUBJECT WHERE ID=?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        return serializer(Integer.parseInt(rs.getString("ID")), rs.getString("NAME"),
                rs.getString("GRP"), rs.getString("HOMEWORK"));
    }
    public List<Subject> getSubject(String name) throws SQLException {
        PreparedStatement st = con.prepareStatement("SELECT * FROM SUBJECT WHERE NAME=?");
        st.setString(1, name);
        ResultSet rs = st.executeQuery();
        List<Subject> ans = new ArrayList<>();
        while (rs.next()) {
            ans.add(serializer(Integer.parseInt(rs.getString("ID")), rs.getString("NAME"),
                    rs.getString("GRP"), rs.getString("HOMEWORK")));
        }
        return ans;
    }
    public List<Subject> getSubjectsByGroup(String grp) throws SQLException {
        PreparedStatement st = con.prepareStatement("SELECT * FROM SUBJECT WHERE GRP=?");
        st.setString(1, grp);
        ResultSet rs = st.executeQuery();
        List<Subject> ans = new ArrayList<>();
        while (rs.next()) {
            ans.add(serializer(Integer.parseInt(rs.getString("ID")), rs.getString("NAME"),
                    rs.getString("GRP"), rs.getString("HOMEWORK")));
        }
        return ans;
    }
    public void deleteSubject(int id) throws SQLException {
        PreparedStatement st = con.prepareStatement("DELETE FROM SUBJECT WHERE ID=?");
        st.setInt(1, id);
        st.execute();
    }
}
