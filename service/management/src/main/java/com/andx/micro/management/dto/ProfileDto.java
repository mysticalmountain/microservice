package com.andx.micro.management.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by andongxu on 16-11-18.
 */
public class ProfileDto implements Serializable{

    private Long id;

    @Max(200)
    @Min(1)
    private Integer age;

    @Max(1)
    @Min(0)
    private Integer sex;

    @Pattern(regexp = "(\\w|[\\u4E00-\\u9FA5]){1,32}", message = "格式:(\\w|[\\u4E00-\\u9FA5]){1,128}")
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
