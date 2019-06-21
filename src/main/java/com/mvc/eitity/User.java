package com.mvc.eitity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Table(name = "userInfo")
public class User {
    @Id
    @Column(name = "id")
   private final int id;
    @Column(name = "name")
//    @Size(min = 2,max = 6,message = "名称不符合要求")
   private String name;
    @Column(name = "age")
//    @Size(min = 1,max = 3,message = "年龄不符合要求")
    @NotNull(message = "年龄不能空")
   private String age;
    @Column(name = "pwd")
    private String passWord;

    public User(int id,String name,String age,String pwd){
        this.id = id;
        this.name = name;
        this.age = age;
        this.passWord = pwd;
    }
    public User(){
        this(0,null,null,null);
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "********id : " + getId() + "***Name :" + getName() + "******* age : " + age;
    }
}
