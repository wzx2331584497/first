package com.bypx.synthesis.bean;

public class accountrole {
 private  String account;
 private  String rolename;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "accountrole{" +
                "account='" + account + '\'' +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}
