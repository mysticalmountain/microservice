package com.andx.micro.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by andongxu on 16-6-14.
 */
@Entity
public class Profile extends BaseEntity{

    @Column(length = 2)
    private int age;

    @Column(length = 1)
    private int sex;

    @Column(length = 256)
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
