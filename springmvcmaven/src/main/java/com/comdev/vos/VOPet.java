package com.comdev.vos;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * Author: gnoloahs
 * Date: 2013-04-09
 * Time: 下午1:19
 */
@Table("t_pet")
public class VOPet
{

    @Name
    private String name;
    private String color;
    private int age;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}
