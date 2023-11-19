package models;

public abstract class ModelField {
    public String name;
    protected String type;
    protected String value;
    public boolean not_null = false;
    public boolean auto_increment = false;
    public boolean primary_key = false;
    public String getSQL(){
        String ans = this.name + " " + this.type;
        if (this.primary_key){
            ans += " NOT NULL AUTO_INCREMENT";
            return ans;
        }
        if (this.not_null){
            ans += " NOT NULL";
        }
        if (this.auto_increment){
            ans += " AUTO_INCREMENT";
        }
        return ans;
    }
    public String getValue(){
        return "'" + value + "'";
    }
}
