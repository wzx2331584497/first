package com.bypx.synthesis.bean;

public class User {
    private  String  role;
    private  String id;
    private  String id_;
    private String name;
private  String account ;
    private  String account_ ;
private String password ;
    private String password_ ;
private user_cls user_cls;
private  String photo_url;
    private String photurl;
    private     String Stringmenu;

    public String getStringmenu() {
        return Stringmenu;
    }

    public void setStringmenu(String stringmenu) {
        Stringmenu = stringmenu;
    }

    public String getPhoturl() {
        return photurl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPhoturl(String photurl) {
        this.photurl = photurl;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getId_() {
        return id_;
    }

    public void setId_(String id_) {
        this.id_ = id_;
    }

    public String getPassword_() {
        return password_;
    }

    public void setPassword_(String password_) {
        this.password_ = password_;
    }

    public String getAccount_() {
        return account_;
    }

    public void setAccount_(String account_) {
        this.account_ = account_;
    }

    public com.bypx.synthesis.bean.user_cls getUser_cls() {
        return user_cls;
    }

    public void setUser_cls(com.bypx.synthesis.bean.user_cls user_cls) {
        this.user_cls = user_cls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
