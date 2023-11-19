package org.example;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import models.*;


public class Main {
    public static void main(String[] args) {
        try{
            Connection con = DriverManager.getConnection("jdbc:h2:file:D:\\IdeaProjects\\Data_bases\\data_bases");

//            Student model = new Student();
//            create(con, model);

//            Student model = new Student();
//            Path path = Path.of("start_db.txt");
//            List<String> list = Files.readAllLines(path);
//            for (String str : list) {
//                String[] parts = str.split(";");
//                model.name.setValue(parts[0]);
//                model.address.setValue(parts[1]);
//                insert(con, model);
//            }

//            Student model = new Student();
//            selectAll(con, model);

//            Student model = new Student();
//            model.name.setValue("Горелов Викентий Тихонович");
//            model.address.setValue("г. Уфа пер. 2-й Благоварский д. 14");
//            insert(con, model);

            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void create(Connection con, Model model) throws Exception {
        Statement st = con.createStatement();
        st.execute(model.createSQL());
    }

    public static void insert(Connection con, Model model) throws Exception {
        Statement st = con.createStatement();
        st.execute(model.insertSQL());
    }

    public static void selectAll(Connection con, Model model){
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(model.selectAllSQL());
            model.printAll(rs);
        }catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}