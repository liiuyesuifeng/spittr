package com.mvc.eitity;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
public class User {
    private final String id;
    @NotNull(message = "{str.notnull}")
    @Size(min = 1,max =6 ,message = "{name.size}")
    private String name;
    @Size(min = 1,max = 3,message = "{age.size}")
    private String age;
    private String message;
    public User(){
        this(null,null);
    }
    public User(String name,String age){
        this.id = null;
        this.name = name;
        this.age = age;
    }
    public String getId() {
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
}
