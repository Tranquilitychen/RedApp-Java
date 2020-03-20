package com.kalic.redapple.pojo;


import java.sql.Timestamp;

public class PayDetailed {

  private String payno;
  private String roomno;
  private String regno;
  private String bookingno;
  private long guestno;
  private double money;
  private String isReceipts;
  private String sourcetype;
  private String paytype;
  private java.sql.Timestamp dtOper;
  private String operid;
  private String meno;


  public String getPayno() {
    return payno;
  }

  public void setPayno(String payno) {
    this.payno = payno;
  }


  public String getRoomno() {
    return roomno;
  }

  public void setRoomno(String roomno) {
    this.roomno = roomno;
  }


  public String getRegno() {
    return regno;
  }

  public void setRegno(String regno) {
    this.regno = regno;
  }


  public String getBookingno() {
    return bookingno;
  }

  public void setBookingno(String bookingno) {
    this.bookingno = bookingno;
  }


  public long getGuestno() {
    return guestno;
  }

  public void setGuestno(long guestno) {
    this.guestno = guestno;
  }


  public double getMoney() {
    return money;
  }

  public void setMoney(double money) {
    this.money = money;
  }


  public String getIsReceipts() {
    return isReceipts;
  }

  public void setIsReceipts(String isReceipts) {
    this.isReceipts = isReceipts;
  }


  public String getSourcetype() {
    return sourcetype;
  }

  public void setSourcetype(String sourcetype) {
    this.sourcetype = sourcetype;
  }


  public String getPaytype() {
    return paytype;
  }

  public void setPaytype(String paytype) {
    this.paytype = paytype;
  }


  public java.sql.Timestamp getDtOper() {
    return dtOper;
  }

  public void setDtOper(java.sql.Timestamp dtOper) {
    this.dtOper = dtOper;
  }


  public String getOperid() {
    return operid;
  }

  public void setOperid(String operid) {
    this.operid = operid;
  }


  public String getMeno() {
    return meno;
  }

  public void setMeno(String meno) {
    this.meno = meno;
  }


  @Override
  public String toString() {
    return "PayDetailed{" +
            "payno='" + payno + '\'' +
            ", roomno='" + roomno + '\'' +
            ", regno='" + regno + '\'' +
            ", bookingno='" + bookingno + '\'' +
            ", guestno=" + guestno +
            ", money=" + money +
            ", isReceipts='" + isReceipts + '\'' +
            ", sourcetype='" + sourcetype + '\'' +
            ", paytype='" + paytype + '\'' +
            ", dtOper=" + dtOper +
            ", operid='" + operid + '\'' +
            ", meno='" + meno + '\'' +
            '}';
  }
}
