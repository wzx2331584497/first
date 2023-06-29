package com.bypx.synthesis.bean;

public class RoleMenuAssociation {
    private  String role;
    private  String checkmenu;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCheckmenu() {
        return checkmenu;
    }

    public void setCheckmenu(String checkmenu) {
        this.checkmenu = checkmenu;
    }

    @Override
    public String toString() {
        return "RoleMenuAssociation{" +
                "role='" + role + '\'' +
                ", checkmenu='" + checkmenu + '\'' +
                '}';
    }
}
