package models;

public class IntField extends ModelField {

    public IntField() {
        this.type = "INT";
    }
    public void setValue(int val){
        value = Integer.toString(val);
    }
}
