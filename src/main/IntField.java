package main;

public class IntField extends ModelField {
    private int value;

    public IntField() {
        this.setValue(0);
        this.type = "INT";
    }
    public void setValue(int val){
        value = val;
    }
    public int getValue(){
        return value;
    }
}
