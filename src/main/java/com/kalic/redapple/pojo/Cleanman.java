package com.kalic.redapple.pojo;


public class Cleanman {

  private String cleanno;
  private String cleanname;
  private String password;
  private String sex;
  private String stopflag;
  private String meno;


  public String getCleanno() {
    return cleanno;
  }

  public void setCleanno(String cleanno) {
    this.cleanno = cleanno;
  }


  public String getCleanname() {
    return cleanname;
  }

  public void setCleanname(String cleanname) {
    this.cleanname = cleanname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public String getStopflag() {
    return stopflag;
  }

  public void setStopflag(String stopflag) {
    this.stopflag = stopflag;
  }


  public String getMeno() {
    return meno;
  }

  public void setMeno(String meno) {
    this.meno = meno;
  }

  @Override
  public String toString() {
    return "Cleanman{" +
            "cleanno='" + cleanno + '\'' +
            ", cleanname='" + cleanname + '\'' +
            ", password='" + password + '\'' +
            ", sex='" + sex + '\'' +
            ", stopflag='" + stopflag + '\'' +
            ", meno='" + meno + '\'' +
            '}';
  }
}
