package models;

public class Student extends Model {
    public CharField name = new CharField(255);
    public CharField address = new CharField(255);

    public Student(){
        name.not_null = true;
    }
}
