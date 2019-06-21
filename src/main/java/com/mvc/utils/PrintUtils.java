package com.mvc.utils;

import java.util.Arrays;
import java.util.List;

public class PrintUtils {

    public static synchronized void print(Object ob){
        System.out.print(ob);
    }
    public static synchronized void println(Object ob){
        System.out.println(ob);
    }
    public static synchronized void printArray(Object ... obs){
        println(Arrays.toString(obs));
    }
    public static synchronized void printList(List<Object> list){
        printArray(list.toArray());
    }
}
