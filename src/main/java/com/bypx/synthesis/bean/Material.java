package com.bypx.synthesis.bean;

import java.util.LinkedList;
import java.util.List;

public class Material {
    private String  id;
    private String  label;
    private String  p_id;
    private String  creat_time;
    private String  level;
    private Boolean    disabled;

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    List<Material> children = new LinkedList<>();
    private int  order;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", p_id='" + p_id + '\'' +
                ", creat_time='" + creat_time + '\'' +
                ", children=" + children +
                ", order=" + order +
                '}';
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<Material> getChildren() {
        return children;
    }

    public void setChildren(List<Material> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }

}
