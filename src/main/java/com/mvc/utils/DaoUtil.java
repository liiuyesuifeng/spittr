package com.mvc.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mvc.eitity.User;
import com.mvc.eitity.base.TableStructure;
import org.junit.Test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.*;

public class DaoUtil {
    /**
     * 表结构Map
     */
    public static final Map<String,TableStructure> tableMap = new HashMap<>();
    /**
     * 封装spring template数据，返回对应实体
     *
     * @param <T>
     * @param tClass
     * @param result
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> List<T> convertSQLResultForEntityList(Class<T> tClass, List<Map<String, Object>> result) throws IllegalAccessException, InstantiationException {
        if(tClass == null || result == null){
            throw new NullPointerException("Class or resultData is null");
        }
        //获取实体
        final Map<String, String> columnFiledMap = columnFiledMap(tClass);
        List<T> resultList = new ArrayList<>();
        Gson gson = JsonUtil.getGson();
        for(Map<String,Object> data : result){
            resultList.add(convertSQLResultForEntity(tClass, data, columnFiledMap));
        }
        return resultList;
    }

    /**
     * 读取实体中注解内容，封装为字段名称和值
     * @param tClass
     * @return
     */
    public static Map<String,String> columnFiledMap(Class tClass){
        Map<String,String> columnFiledMap = new LinkedHashMap<>(16);
        Field[] fields = tClass.getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            Column annotation = field.getAnnotation(Column.class);
            if(annotation != null){
                columnFiledMap.put(annotation.name(),field.getName());
            }
        }
        return columnFiledMap;
    }

    /**
     * 拼接字段和值转化实体
     * 实体的转换没有用到SET方法去注入
     * 而是选择了使用MAP通过JSON直接转化为实体
     * @param tClass
     * @param data
     * @param columnFiledMap
     * @param <T>
     * @return
     */
    public static <T> T convertSQLResultForEntity(Class<T> tClass,Map<String,Object> data,Map<String,String> columnFiledMap){
        Map<Object,Object> newObjectValue = new HashMap<>();
        for(Map.Entry<String,Object> entry : data.entrySet()){
            String newFiled = columnFiledMap.get(entry.getKey());
            newObjectValue.put(newFiled,entry.getValue());
        }
        return JsonUtil.formJson(JsonUtil.formatJson(newObjectValue), tClass);

    }

    /**
     * 转化实体为SQL，保存方法
     * @param ob
     * @return
     * @throws IllegalAccessException
     * 这个方法有缺陷，生成的SQL字段顺序不是和数据库中对应的，后续准备优化
     */
    public static synchronized String convertEntityForSQL(Object ob) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer("insert into ");
        Class<?> aClass = ob.getClass();//获取传入对象的Class
        Field[] fields = aClass.getDeclaredFields();//反射获取对象的私有化字段
        Table entity = aClass.getAnnotation(Table.class);//获取实体中表名
        sql.append(entity.name());
        TreeSet<String> rowSet = new TreeSet<>();
        LinkedList<Object> valSet = new LinkedList<>();
        for(Field field : fields){//这里不想写两次循环所以没有调用上面的方法
            field.setAccessible(true);
            Column annotation = field.getAnnotation(Column.class);//获取Column注解的字段名称
            Id id = field.getAnnotation(Id.class);
            if(annotation != null){
                if(id!= null){
                    continue;//判断是否有ID注解，因为数据库里面id设置为自增所以拼接SQL的时候不用拼接ID
                }
                rowSet.add(annotation.name());
                valSet.add(String.valueOf(field.get(ob)));
            }
        }
        sql.append(disposeRowSQL(rowSet.toString()));
        sql.append(" VALUES (");
        for(Object val :valSet){
            sql.append("'" + val + "',");
        }
        if(sql.toString().endsWith(",")){
            sql.replace(sql.length() -1,sql.length(),"");
        }
        sql.append(")");
        return sql.toString();
    }
    public static <T> T convertSQLResultForEntity(Class<T> tClass,Map<String,Object> data){
        Map<String, String> stringStringMap = columnFiledMap(tClass);
        return convertSQLResultForEntity(tClass,data,stringStringMap);
    }

    private static String disposeRowSQL(String arg){
        return arg.replaceAll("\\[","(").replaceAll("\\]",")");
    }
    @Test
    public void junitOne() throws IllegalAccessException {

    }
    @Test
    public void daoTest()  throws IllegalAccessException {
        String s = convertEntityForSQL(new User(0, "a", "a", "b"));
        System.out.println(s);
    }
}
