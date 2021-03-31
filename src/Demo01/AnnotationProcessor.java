package Demo01;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.*;

/**
 * @author KIKOFranklin
 * @create 2021/3/30 0030 22:14
 */
public class AnnotationProcessor {
    private static String creatTableSql(final String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        DBtable dBtable = clazz.getAnnotation(DBtable.class);
        if(dBtable == null){
            return "No @DBtable annotation detected, please check whether you have put @DBtable at the top of the" + className;
        }
        //设置表名
        String tableName = dBtable.name();
        if(tableName.length() < 1){
            tableName = clazz.getName().toUpperCase();
        }

        List<String> columnDefs = new ArrayList<>();
        for(Field field : clazz.getDeclaredFields()){
            String columnName = null;
            //判断有没有注解
            Annotation[] annos = field.getDeclaredAnnotations();
            if(annos.length < 1) continue;
            //如果是Integer的情况
            if(annos[0] instanceof SQLInteger){
                SQLInteger sqlInteger = (SQLInteger) annos[0];
                if(sqlInteger.name().equals("")){
                    columnName = field.getName().toUpperCase();
                } else {
                    columnName = sqlInteger.name();
                }
                columnDefs.add(columnName + " INT" + getConstraints(sqlInteger.constraints()));
            }
            //如果是String的情况
            if(annos[0] instanceof SQLstring){
                SQLstring sqLstring = (SQLstring) annos[0];
                if(sqLstring.name().equals("")){
                    columnName = field.getName().toUpperCase();
                } else {
                    columnName = sqLstring.name();
                }
                columnDefs.add(columnName + " VARCHAR("+sqLstring.length()+")" + getConstraints(sqLstring.constraints()));
            }
        }
        //数据库表构建语句
        StringBuilder createCommand = new StringBuilder(
                "CREATE TABLE " + tableName + "(");
        for(String columnDef : columnDefs)
            createCommand.append("\n    " + columnDef + ",");
        System.out.println();
        // Remove trailing comma
        return createCommand.substring(0, createCommand.length() - 1) + ");";
    }
    /**
     * 判断该字段是否有其他约束
     * @param con
     * @return
     */
    private static String getConstraints(Constraints con) {
        String constraints = "";
        if(!con.isAllowNull())
            constraints += " NOT NULL";
        if(con.isPrimaryKey())
            constraints += " PRIMARY KEY";
        if(con.isUnique())
            constraints += " UNIQUE";
        return constraints;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(creatTableSql("Demo01.Test01"));
    }
}
