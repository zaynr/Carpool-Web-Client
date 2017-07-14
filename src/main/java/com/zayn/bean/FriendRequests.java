package com.zayn.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by zaynr on 2017/7/14.
 */
@Entity
public class FriendRequests {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int serial_num;

    private int userial1,userial2;

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
