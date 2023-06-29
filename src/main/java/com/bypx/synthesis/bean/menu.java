package com.bypx.synthesis.bean;
import java.util.LinkedList;
import java.util.List;
public class menu {
    private  String level;
    private String id;
    private String label;
    private String url;
    private String pid;
    private int order1;
    private String icon;
    private menu menus1;
    private String fatherid;
    private int link;
    List<menu> children = new LinkedList<>();
    @Override
    public String toString() {
        return "menu{" +
                "level='" + level + '\'' +
                ", id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", url='" + url + '\'' +
                ", pid='" + pid + '\'' +
                ", order=" + order1 +
                ", icon='" + icon + '\'' +
                ", menus1=" + menus1 +
                ", link=" + link +
                ", children=" + children +
                '}';
    }
    public String getFatherid() {
        return fatherid;
    }
    public void setFatherid(String fatherid) {
        this.fatherid = fatherid;
    }
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getOrder1() {
        return order1;
    }

    public void setOrder1(int order1) {
        this.order1 = order1;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public menu getMenus1() {
        return menus1;
    }

    public void setMenus1(menu menus1) {
        this.menus1 = menus1;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public List<menu> getChildren() {
        return children;
    }

    public void setChildren(List<menu> children) {
        this.children = children;
    }
}