package com.mvc.test.controller;

import com.mvc.eitity.User;
import com.mvc.utils.PrintUtils;
import org.junit.Test;
import org.thymeleaf.expression.Lists;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;
import java.util.stream.Collectors;

public class JunitTest {
    @Test
    public void test1(){
        String [] aa = {"a","b","c","d"};
        List<String> list = Arrays.asList(aa);
//        for(String a : list){
//            continue;
//        }
        Set<String> bb = new HashSet<>(list);
        Map<String,String> map = new HashMap<>();
//        LambdaT t = (String a) -> {PrintUtils.println(a);};
//        t.test1("b");

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
    // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        squaresList.forEach(System.out::println);
        String c = "aa";
    }
    public boolean isB(String arg){
        return arg.equals("b");
    }
}
interface LambdaT{
    public void test1(String a);
}
class LambdaM implements LambdaT{
    public void test1(String a){
        PrintUtils.println("Driver dmo");
    }
}
class LambdaF implements LambdaT{
    public void test1(String a){
        PrintUtils.println("Driver dmo");
    }
}
