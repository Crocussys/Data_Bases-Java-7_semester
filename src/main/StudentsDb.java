package main;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class StudentsDb {
    private ConnectionSource connectionSource;

    private Dao<Student, String> studentDao;

    private Dao<Subject, String> subjectDao;

    public StudentsDb(){}

    public void connect(String databaseUrl) throws SQLException {
        connectionSource = new JdbcConnectionSource(databaseUrl);
        studentDao = DaoManager.createDao(connectionSource, Student.class);
        subjectDao = DaoManager.createDao(connectionSource, Subject.class);
    }

    public void disconnect() throws Exception {
        connectionSource.close();
    }

    public void createTables() throws SQLException {
        TableUtils.createTable(connectionSource, Student.class);
        TableUtils.createTable(connectionSource, Subject.class);
    }

    public Dao<Student, String> getStudentDao(){
        return studentDao;
    }

    public Dao<Subject, String> getSubjectDao(){
        return subjectDao;
    }
}
