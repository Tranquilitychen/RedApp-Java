package com.kalic.redapple.pojo;


import java.sql.Timestamp;

public class RegBill {

  private long flowid;
  private String roomno;
  private String regno;
  private String itemno;
  private String itemname;
  private long num;
  private double price;
  private double discount;
  private double totalprice;
  private java.sql.Timestamp dtIndate;
  private java.sql.Timestamp dtOutdate;
  private java.sql.Timestamp dtOper;
  private String operid;
  private String meno;
  private String nullify;


  public long getFlowid() {
    return flowid;
  }

  public void setFlowid(long flowid) {
    this.flowid = flowid;
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


  public String getItemno() {
    return itemno;
  }

  public void setItemno(String itemno) {
    this.itemno = itemno;
  }


  public String getItemname() {
    return itemname;
  }

  public void setItemname(String itemname) {
    this.itemname = itemname;
  }


  public long getNum() {
    return num;
  }

  public void setNum(long num) {
    this.num = num;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }


  public double getTotalprice() {
    return totalprice;
  }

  public void setTotalprice(double totalprice) {
    this.totalprice = totalprice;
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


  public String getNullify() {
    return nullify;
  }

  public void setNullify(String nullify) {
    this.nullify = nullify;
  }

  public Timestamp getDtIndate() {
    return dtIndate;
  }

  public void setDtIndate(Timestamp dtIndate) {
    this.dtIndate = dtIndate;
  }

  public Timestamp getDtOutdate() {
    return dtOutdate;
  }

  public void setDtOutdate(Timestamp dtOutdate) {
    this.dtOutdate = dtOutdate;
  }

  @Override
  public String toString() {
    return "RegBill{" +
            "flowid=" + flowid +
            ", roomno='" + roomno + '\'' +
            ", regno='" + regno + '\'' +
            ", itemno='" + itemno + '\'' +
            ", itemname='" + itemname + '\'' +
            ", num=" + num +
            ", price=" + price +
            ", discount=" + discount +
            ", totalprice=" + totalprice +
            ", dtIndate=" + dtIndate +
            ", dtOutdate=" + dtOutdate +
            ", dtOper=" + dtOper +
            ", operid='" + operid + '\'' +
            ", meno='" + meno + '\'' +
            ", nullify='" + nullify + '\'' +
            '}';
  }
}
