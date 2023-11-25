package main;

public class CharField extends ModelField {
    private String value;
    public CharField(int max_length) {
        this.setValue("");
        this.type = "VARCHAR(" + max_length + ")";
    }
    public void setValue(String val){
        value = val;
    }
    public String getValue(){
        return value;
    }
}
