package com.example.administrator.jdadrress;

/**
 * Created by Administrator on 2017/9/12.
 */

public class ListBean {
    String name;
    String addr;
    String phone;
    String addr_detail;

    @Override
    public String toString() {
        return "ListBean{" +
                "name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", phone='" + phone + '\'' +
                ", addr_detail='" + addr_detail + '\'' +
                '}';
    }

    public String getAddr_detail() {
        return addr_detail;
    }

    public void setAddr_detail(String addr_detail) {
        this.addr_detail = addr_detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
