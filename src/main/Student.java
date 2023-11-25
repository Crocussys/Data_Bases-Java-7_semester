package main;

public class Student{
    public IntField id = new IntField();
    public CharField name = new CharField(255);
    public CharField grp = new CharField(31);
    public CharField address = new CharField(255);
    public IntField age = new IntField();

    public Student(){
        id.primary_key = true;
        name.not_null = true;
    }
    public Student(String name, String grp){
        this();
        this.name.setValue(name);
        this.grp.setValue(grp);
    }
}
