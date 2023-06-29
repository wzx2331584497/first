package com.bypx.synthesis.bean;

public class jxcproduct {
private  String id;
    private  String typeid;
    private  int total;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "jxcproduct{" +
                "id='" + id + '\'' +
                ", typeid='" + typeid + '\'' +
                ", total=" + total +
                '}';
    }
}
