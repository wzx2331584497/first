package com.bypx.synthesis.bean;

import java.util.Date;

public class jxcproductorder {

    private  String id;
    private  String typeid;
    private  int number;
    private  int price;
    private  String creater;
    private  String createrid;
    private  int ordertype;
    private Date createdate;
    private  int status;
    private   String statuss;
    private  String Stringdate;
    private  String cause;

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getStringdate() {
        return Stringdate;
    }

    public void setStringdate(String stringdate) {
        Stringdate = stringdate;
    }

    public String getStatuss() {
        return statuss;
    }

    public void setStatuss(String statuss) {
        this.statuss = statuss;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCreaterid() {
        return createrid;
    }

    public void setCreaterid(String createrid) {
        this.createrid = createrid;
    }

    public int getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    @Override
    public String toString() {
        return "jxcproductorder{" +
                "id='" + id + '\'' +
                ", typeid='" + typeid + '\'' +
                ", number=" + number +
                ", price=" + price +
                ", creater='" + creater + '\'' +
                ", createrid='" + createrid + '\'' +
                ", ordertype=" + ordertype +
                ", createdate=" + createdate +
                ", status=" + status +
                ", statuss='" + statuss + '\'' +
                ", Stringdate='" + Stringdate + '\'' +
                ", cause='" + cause + '\'' +
                '}';
    }
}
