package com.kalic.redapple.vo;

/**
 * @author Kalic Li
 * @ClassName LoginInfo
 * @Package com.kalic.redapple.vo
 * @Description TODO
 * @date 2020/2/29 14:24
 */
public class LoginInfo {
    private String username;
    private String password;
    private Integer role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
