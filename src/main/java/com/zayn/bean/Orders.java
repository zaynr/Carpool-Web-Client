package com.zayn.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zengzy19585 on 2017/7/13.
 */
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int serial_num;

    private String call_serial,rec_mobile_num,call_type,ori_lat,
        ori_lng,des_lat,des_lng,status, ori_address, des_address;

    public String getOri_address() {
        return ori_address;
    }

    public void setOri_address(String ori_address) {
        this.ori_address = ori_address;
    }

    public String getDes_address() {
        return des_address;
    }

    public void setDes_address(String des_address) {
        this.des_address = des_address;
    }

    @Column(name="apt_time")
    private Date apt_time;

    public int getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(int serial_num) {
        this.serial_num = serial_num;
    }

    public String getCall_serial() {
        return call_serial;
    }

    public void setCall_serial(String call_serial) {
        this.call_serial = call_serial;
    }

    public String getRec_mobile_num() {
        return rec_mobile_num;
    }

    public void setRec_mobile_num(String rec_mobile_num) {
        this.rec_mobile_num = rec_mobile_num;
    }

    public String getCall_type() {
        return call_type;
    }

    public void setCall_type(String call_type) {
        this.call_type = call_type;
    }

    public Date getApt_time() {
        return apt_time;
    }

    public void setApt_time(Date apt_time) {
        this.apt_time = apt_time;
    }

    public String getOri_lat() {
        return ori_lat;
    }

    public void setOri_lat(String ori_lat) {
        this.ori_lat = ori_lat;
    }

    public String getOri_lng() {
        return ori_lng;
    }

    public void setOri_lng(String ori_lng) {
        this.ori_lng = ori_lng;
    }

    public String getDes_lat() {
        return des_lat;
    }

    public void setDes_lat(String des_lat) {
        this.des_lat = des_lat;
    }

    public String getDes_lng() {
        return des_lng;
    }

    public void setDes_lng(String des_lng) {
        this.des_lng = des_lng;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
