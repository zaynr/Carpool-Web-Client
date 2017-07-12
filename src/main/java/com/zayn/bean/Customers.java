package com.zayn.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by zengzy19585 on 2017/7/12.
 */
@Entity
public class Customers {
    @Id
    private String serial_num;

    private String user_name, sex, mobile_number, pwd;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String name) {
        this.user_name = name;
    }

    public String getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
