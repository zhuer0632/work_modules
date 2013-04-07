package com.me.vo;

import java.util.Date;
import java.util.List;

public class VOPerson {

    private String name;

    private Integer age;

    private List<String> phone;

    private Date birthday;
    
    
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Integer getAge() {
	return age;
    }

    public void setAge(Integer age) {
	this.age = age;
    }

    public List<String> getPhone() {
	return phone;
    }

    public void setPhone(List<String> phone) {
	this.phone = phone;
    }

}
