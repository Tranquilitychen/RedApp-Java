package com.kalic.redapple.pojo;


public class Salesman {

  private String salemanno;
  private String salemanname;
  private String password;
  private String sex;
  private String tel;
  private String mobile;
  private String address;
  private String stopflag;
  private String meno;


  public String getSalemanno() {
    return salemanno;
  }

  public void setSalemanno(String salemanno) {
    this.salemanno = salemanno;
  }


  public String getSalemanname() {
    return salemanname;
  }

  public void setSalemanname(String salemanname) {
    this.salemanname = salemanname;
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


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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
    return "Salesman{" +
            "salemanno='" + salemanno + '\'' +
            ", salemanname='" + salemanname + '\'' +
            ", password='" + password + '\'' +
            ", sex='" + sex + '\'' +
            ", tel='" + tel + '\'' +
            ", mobile='" + mobile + '\'' +
            ", address='" + address + '\'' +
            ", stopflag='" + stopflag + '\'' +
            ", meno='" + meno + '\'' +
            '}';
  }
}
