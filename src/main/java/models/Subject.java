package models;

public class Subject {
    public IntField id = new IntField();
    public CharField name = new CharField(255);
    public CharField grp = new CharField(31);
    public CharField homework = new CharField(511);
    public Subject(){
        id.primary_key = true;
        name.not_null = true;
        grp.not_null = true;
    }
}
