package com.zayn.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by zengzy19585 on 2017/7/13.
 */
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int serial_num;

    private String call_serial,rec_mobile_num,call_type,apt_time,ori_lat,
        ori_lng,des_lat,des_lng,status;

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

    public String getApt_time() {
        return apt_time;
    }

    public void setApt_time(String apt_time) {
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
