package com.zayn.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by zaynr on 2017/7/14.
 */
@Entity
public class Friends {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int serial_num;
    private int userial1;
    private int userial2;
    private int serve_count;
    private String rec_mobile_num, call_serial;

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

    public int getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(int serial_num) {
        this.serial_num = serial_num;
    }

    public int getUserial1() {
        return userial1;
    }

    public void setUserial1(int userial1) {
        this.userial1 = userial1;
    }

    public int getUserial2() {
        return userial2;
    }

    public void setUserial2(int userial2) {
        this.userial2 = userial2;
    }
}
