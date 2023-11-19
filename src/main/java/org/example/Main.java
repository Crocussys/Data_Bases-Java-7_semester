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
            db.getSubjectDao().createTable();

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

            path = Path.of("start_db2.txt");
            list = Files.readAllLines(path);
            Subject model2 = new Subject();
            for (String str : list) {
                String[] parts = str.split(";");
                model2.name.setValue(parts[0]);
                model2.grp.setValue(parts[1]);
                db.getSubjectDao().insertSubject(model2);
            }

            PrintStudents(db.getStudentDao().getStudentsByGroup("20ПМ"));
            PrintSubjects(db.getSubjectDao().getSubjectsByGroup("20ПМ"));

            model.name.setValue("Горелов Викентий Тихонович");
            model.address.setValue("г. Уфа пер. 2-й Благоварский д. 14");
            model.age.setValue(17);
            model.grp.setValue("23ПМ");
            db.getStudentDao().insertStudent(model);

            model2.name.setValue("Базы данных");
            model2.grp.setValue("19ПМ");
            model2.homework.setValue("Сделать лабу 2.3");
            db.getSubjectDao().insertSubject(model2);

            System.out.println();
            PrintStudents(db.getStudentDao().getStudentsByGroup("23ПМ"));
            PrintSubjects(db.getSubjectDao().getSubjectsByGroup("19ПМ"));

            System.out.println();
            PrintStudents(db.getStudentDao().getStudents(18, 25));
            PrintSubjects(db.getSubjectDao().getSubject("Численные методы"));

            System.out.println();
            db.getStudentDao().deleteStudent(8);
            db.getSubjectDao().deleteSubject(3);
            PrintStudents(db.getStudentDao().getStudentsByGroup("20ПМ"));
            PrintSubjects(db.getSubjectDao().getSubjectsByGroup("20ПМ"));
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
    public static void PrintSubjects(List<Subject> subjects){
        for (Subject subject : subjects) {
            System.out.println(subject.id.getValue() + " " + subject.name.getValue() + " " +
                    subject.grp.getValue() + " " + subject.homework.getValue());
        }
    }
}