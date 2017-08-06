package com.zayn.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DriverServes {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int serial_num;

    private int serve_count;
    private String rec_mobile_num, call_serial, call_mobile_num, call_name;

    public int getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(int serial_num) {
        this.serial_num = serial_num;
    }

    public String getCall_mobile_num() {
        return call_mobile_num;
    }

    public void setCall_mobile_num(String call_mobile_num) {
        this.call_mobile_num = call_mobile_num;
    }

    public String getCall_name() {
        return call_name;
    }

    public void setCall_name(String call_name) {
        this.call_name = call_name;
    }

    public int getServe_count() {
        return serve_count;
    }

    public void setServe_count(int serve_count) {
        this.serve_count = serve_count;
    }

    public String getRec_mobile_num() {
        return rec_mobile_num;
    }

    public void setRec_mobile_num(String rec_mobile_num) {
        this.rec_mobile_num = rec_mobile_num;
    }

    public String getCall_serial() {
        return call_serial;
    }

    public void setCall_serial(String call_serial) {
        this.call_serial = call_serial;
    }
}
