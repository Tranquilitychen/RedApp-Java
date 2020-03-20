package com.kalic.redapple.pojo;


public class Guest {

  private long guestno;
  private String guestname;
  private String idtypeno;
  private String idno;
  private String sex;
  private java.sql.Timestamp dtBirthday;
  private String countryno;
  private String areano;
  private String portentryno;
  private String address;
  private String imgPhoto;
  private String meno;
  private String tel;
  private String groupflag;


  public long getGuestno() {
    return guestno;
  }

  public void setGuestno(long guestno) {
    this.guestno = guestno;
  }


  public String getGuestname() {
    return guestname;
  }

  public void setGuestname(String guestname) {
    this.guestname = guestname;
  }


  public String getIdtypeno() {
    return idtypeno;
  }

  public void setIdtypeno(String idtypeno) {
    this.idtypeno = idtypeno;
  }


  public String getIdno() {
    return idno;
  }

  public void setIdno(String idno) {
    this.idno = idno;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public java.sql.Timestamp getDtBirthday() {
    return dtBirthday;
  }

  public void setDtBirthday(java.sql.Timestamp dtBirthday) {
    this.dtBirthday = dtBirthday;
  }


  public String getCountryno() {
    return countryno;
  }

  public void setCountryno(String countryno) {
    this.countryno = countryno;
  }


  public String getAreano() {
    return areano;
  }

  public void setAreano(String areano) {
    this.areano = areano;
  }


  public String getPortentryno() {
    return portentryno;
  }

  public void setPortentryno(String portentryno) {
    this.portentryno = portentryno;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getImgPhoto() {
    return imgPhoto;
  }

  public void setImgPhoto(String imgPhoto) {
    this.imgPhoto = imgPhoto;
  }


  public String getMeno() {
    return meno;
  }

  public void setMeno(String meno) {
    this.meno = meno;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getGroupflag() {
    return groupflag;
  }

  public void setGroupflag(String groupflag) {
    this.groupflag = groupflag;
  }

  @Override
  public String toString() {
    return "Guest{" +
            "guestno=" + guestno +
            ", guestname='" + guestname + '\'' +
            ", idtypeno='" + idtypeno + '\'' +
            ", idno='" + idno + '\'' +
            ", sex='" + sex + '\'' +
            ", dtBirthday=" + dtBirthday +
            ", countryno='" + countryno + '\'' +
            ", areano='" + areano + '\'' +
            ", portentryno='" + portentryno + '\'' +
            ", address='" + address + '\'' +
            ", imgPhoto='" + imgPhoto + '\'' +
            ", meno='" + meno + '\'' +
            ", tel='" + tel + '\'' +
            ", groupflag='" + groupflag + '\'' +
            '}';
  }
}
