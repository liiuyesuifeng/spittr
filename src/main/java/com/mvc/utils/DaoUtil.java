package com.mvc.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mvc.eitity.User;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.*;

public class DaoUtil {
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
        final Map<String, String> columnFiledMap = columnFiledMap(tClass);
        List<T> resultList = new ArrayList<>();
        Gson gson = JsonUtil.getGson();
        for(Map<String,Object> data : result){
            resultList.add(convertSQLResultForEntity(tClass, data, columnFiledMap));
        }
        return resultList;
    }
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
    public static <T> T convertSQLResultForEntity(Class<T> tClass,Map<String,Object> data,Map<String,String> columnFiledMap){
        Map<Object,Object> newObjectValue = new HashMap<>();
        for(Map.Entry<String,Object> entry : data.entrySet()){
            String newFiled = columnFiledMap.get(entry.getKey());
            newObjectValue.put(newFiled,entry.getValue());
        }
        return JsonUtil.formJson(JsonUtil.formatJson(newObjectValue), tClass);

    }
    public static <T> T convertSQLResultForEntity(Class<T> tClass,Map<String,Object> data){
        Map<String, String> stringStringMap = columnFiledMap(tClass);
        return convertSQLResultForEntity(tClass,data,stringStringMap);
    }
}
