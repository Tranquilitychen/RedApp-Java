package com.kalic.redapple.vo;

import java.sql.Timestamp;

/**
 * @author Kalic Li
 * @ClassName RegBillInfo
 * @Package com.kalic.redapple.vo
 * @Description TODO
 * @date 2020/3/10 19:11
 */
public class RegBillInfo {
    private String roomno;
    private String regno;
    private String itemno;
    private String itemname;
    private long num;
    private double price;
    private double discount;
    private double totalprice;
    private java.sql.Timestamp dtOper;
    private String operid;
    private boolean disabled;
    private String updateKbn;
    private String nullify;

    public String getNullify() {
        return nullify;
    }

    public void setNullify(String nullify) {
        this.nullify = nullify;
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

    public Timestamp getDtOper() {
        return dtOper;
    }

    public void setDtOper(Timestamp dtOper) {
        this.dtOper = dtOper;
    }

    public String getOperid() {
        return operid;
    }

    public void setOperid(String operid) {
        this.operid = operid;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getUpdateKbn() {
        return updateKbn;
    }

    public void setUpdateKbn(String updateKbn) {
        this.updateKbn = updateKbn;
    }

    @Override
    public String toString() {
        return "RegBillInfo{" +
                "roomno='" + roomno + '\'' +
                ", regno='" + regno + '\'' +
                ", itemno='" + itemno + '\'' +
                ", itemname='" + itemname + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", discount=" + discount +
                ", totalprice=" + totalprice +
                ", dtOper=" + dtOper +
                ", operid='" + operid + '\'' +
                ", disabled=" + disabled +
                ", updateKbn='" + updateKbn + '\'' +
                '}';
    }
}
