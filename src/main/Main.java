package main;

import com.j256.ormlite.dao.CloseableWrappedIterable;
import com.j256.ormlite.dao.Dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        StudentsDb db = new StudentsDb();
        db.connect("jdbc:h2:file:" + System.getProperty("user.dir") + "\\students.db");

        db.createTables();

        Path path = Path.of("start_db.txt");
        List<String> list = Files.readAllLines(path);
        for (String str : list) {
            String[] parts = str.split(";");
            Student model = new Student(parts[0], 21);
            model.setAddress(parts[1]);
            model.setGrp("20ПМ");
            db.getStudentDao().create(model);
        }

        path = Path.of("start_db2.txt");
        list = Files.readAllLines(path);
        for (String str : list) {
            String[] parts = str.split(";");
            Subject model = new Subject(parts[0], parts[1]);
            db.getSubjectDao().create(model);
        }

        PrintStudents(db.getStudentDao());
        PrintSubjects(db.getSubjectDao());

        Student model = new Student("Горелов Викентий Тихонович", 17);
        model.setAddress("г. Уфа пер. 2-й Благоварский д. 14");
        model.setGrp("23ПМ");
        db.getStudentDao().create(model);

        Subject model2 = new Subject("Базы данных", "19ПМ");
        model2.setHomework("Сделать лабу 2.3");
        db.getSubjectDao().create(model2);

        db.getStudentDao().delete(db.getStudentDao().queryForId("8"));
        db.getSubjectDao().delete(db.getSubjectDao().queryForId("3"));

        System.out.println();
        PrintStudents(db.getStudentDao());
        PrintSubjects(db.getSubjectDao());

        db.disconnect();
    }
    public static void PrintStudents(Dao<Student, String> studentDao) throws Exception {
        try (CloseableWrappedIterable<Student> ignored = studentDao.getWrappedIterable()) {
            for (Student student : studentDao) {
                System.out.println(student.getId() + " " + student.getName() + " " + student.getGrp() + " " +
                        student.getAddress() + " " + student.getAge());
            }
        }
    }
    public static void PrintSubjects(Dao<Subject, String> subjectDao) throws Exception {
        try (CloseableWrappedIterable<Subject> ignored = subjectDao.getWrappedIterable()) {
            for (Subject subject : subjectDao) {
                System.out.println(subject.getId() + " " + subject.getName() + " " + subject.getGrp() + " " +
                        subject.getHomework());
            }
        }
    }
}
