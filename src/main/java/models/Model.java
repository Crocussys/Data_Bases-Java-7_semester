package models;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Model {
    private ModelField[] getFields() throws IllegalAccessException {
        ModelField id = new IntField();
        id.name = "ID";
        id.primary_key = true;
        Field[] fields = this.getClass().getFields();
        int count_fields = fields.length + 1;
        ModelField[] ans = new ModelField[count_fields];
        ans[0] = id;
        for(int i = 1; i < count_fields; i++){
            ans[i] = (ModelField) fields[i - 1].get(this);
            ans[i].name = fields[i - 1].getName().toUpperCase();
        }
        return ans;
    }
    public String createSQL() throws Exception {
        StringBuilder ans = new StringBuilder("CREATE TABLE " + this.getClass().getSimpleName().toUpperCase() +"(" );
        ModelField[] fields = getFields();
        for (ModelField field : fields) {
            ans.append(field.getSQL()).append(",");
        }
        boolean flag = true;
        for (ModelField field : fields) {
            if (field.primary_key) {
                if (flag) {
                    ans.append("PRIMARY KEY(").append(field.name).append(")");
                    flag = false;
                } else {
                    throw new Exception("Primary key может быть только один");
                }
            }
        }
        if (flag){
            ans.deleteCharAt(ans.length() - 1);
        }
        ans.append(")");
        return ans.toString();
    }

    public String insertSQL() throws IllegalAccessException {
        StringBuilder ans = new StringBuilder("INSERT INTO " + this.getClass().getSimpleName().toUpperCase() + " (");
        ModelField[] fields = getFields();
        int count_fields = fields.length;
        for(int i = 1; i < count_fields; i++){
            ans.append(fields[i].name).append(",");
        }
        ans.deleteCharAt(ans.length() - 1).append(") VALUES (");
        for(int i = 1; i < count_fields; i++){
            ans.append(fields[i].getValue()).append(",");
        }
        ans.deleteCharAt(ans.length() - 1).append(")");
        return ans.toString();
    }

    public String selectAllSQL(){
        return "SELECT * FROM " + this.getClass().getSimpleName().toUpperCase();
    }

    public void printAll(ResultSet rs) throws SQLException, IllegalAccessException {
        ModelField[] fields = getFields();
        int count_fields = fields.length;
        while (rs.next()) {
            StringBuilder ans = new StringBuilder();
            for(int i = 1; i < count_fields; i++){
                ans.append(rs.getString(fields[i].name)).append(" ");
            }
            System.out.println(ans);
        }
    }
}

