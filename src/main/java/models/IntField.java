package models;

public class IntField extends ModelField {

    public IntField() {
        this.setValue(0);
        this.type = "INT";
    }
    public void setValue(int val){
        value = Integer.toString(val);
    }
}
