package models;

public class CharField extends ModelField {
    public CharField(int max_length) {
        this.setValue("");
        this.type = "VARCHAR(" + Integer.toString(max_length) + ")";
    }
    public void setValue(String val){
        value = "'" + val + "'";
    }
}
