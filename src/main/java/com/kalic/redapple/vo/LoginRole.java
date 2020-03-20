package com.kalic.redapple.vo;

/**
 * @author Kalic Li
 * @ClassName LoginRole
 * @Package com.kalic.redapple.vo
 * @Description TODO
 * @date 2020/3/7 17:48
 */
public class LoginRole {
    private String uid;
    private String username;
    private String role;
    private String menuList;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMenuList() {
        return menuList;
    }

    public void setMenuList(String menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "LoginRole{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", menuList='" + menuList + '\'' +
                '}';
    }
}

