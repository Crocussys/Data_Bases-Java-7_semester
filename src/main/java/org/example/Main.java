package org.example;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import models.*;


public class Main {
    public static void main(String[] args) {
        try{
            StudentsDb db = new StudentsDb();
            db.connect("jdbc:h2:file:D:\\IdeaProjects\\Data_bases\\data_bases");

            db.getStudentDao().createTable();

            Path path = Path.of("start_db.txt");
            List<String> list = Files.readAllLines(path);
            Student model = new Student();
            for (String str : list) {
                String[] parts = str.split(";");
                model.name.setValue(parts[0]);
                model.address.setValue(parts[1]);
                model.age.setValue(21);
                model.grp.setValue("20ПМ");
                db.getStudentDao().insertStudent(model);
            }

            PrintStudents(db.getStudentDao().getStudentsByGroup("20ПМ"));

            model.name.setValue("Горелов Викентий Тихонович");
            model.address.setValue("г. Уфа пер. 2-й Благоварский д. 14");
            model.age.setValue(17);
            model.grp.setValue("23ПМ");
            db.getStudentDao().insertStudent(model);

            System.out.println();
            PrintStudents(db.getStudentDao().getStudentsByGroup("23ПМ"));

            System.out.println();
            PrintStudents(db.getStudentDao().getStudents(18, 25));

            System.out.println();
            db.getStudentDao().deleteStudent(8);
            PrintStudents(db.getStudentDao().getStudentsByGroup("20ПМ"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void PrintStudents(List<Student> students){
        for (Student student : students) {
            System.out.println(student.id.getValue() + " " + student.name.getValue() + " " +
                    student.grp.getValue() + " " + student.address.getValue() + " " + student.age.getValue());
        }
    }
}