package main;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable()
public class Student{
    @DatabaseField(generatedId = true, canBeNull = false)
    private int id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField()
    private String grp;

    @DatabaseField()
    private String address;

    @DatabaseField(canBeNull = false)
    private int age;

    public Student(){}

    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrp() {
        return grp;
    }

    public void setGrp(String grp) {
        this.grp = grp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
