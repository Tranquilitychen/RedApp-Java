package com.kalic.redapple.vo;

import java.sql.Timestamp;

/**
 * @author Kalic Li
 * @ClassName Roomqty
 * @Package com.kalic.redapple.vo
 * @Description TODO
 * @date 2020/2/20 19:30
 */
public class Roomqty {
    private String regno;
    private Timestamp newDtOutdate;
    private Timestamp dtOutdate;
    private String roomno;
    private String roomqty;
    private double regPrice;
    private String rstatus;     //逾期
    private String roomlbname;
    private Timestamp dtIndate;
    private double payPrice;
    private int days;
    private String guestname;
    private String tel;

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public Timestamp getNewDtOutdate() {
        return newDtOutdate;
    }

    public void setNewDtOutdate(Timestamp newDtOutdate) {
        this.newDtOutdate = newDtOutdate;
    }

    public Timestamp getDtOutdate() {
        return dtOutdate;
    }

    public void setDtOutdate(Timestamp dtOutdate) {
        this.dtOutdate = dtOutdate;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public String getRoomqty() {
        return roomqty;
    }

    public void setRoomqty(String roomqty) {
        this.roomqty = roomqty;
    }

    public double getRegPrice() {
        return regPrice;
    }

    public void setRegPrice(double regPrice) {
        this.regPrice = regPrice;
    }

    public String getRstatus() {
        return rstatus;
    }

    public void setRstatus(String rstatus) {
        this.rstatus = rstatus;
    }

    public String getRoomlbname() {
        return roomlbname;
    }

    public void setRoomlbname(String roomlbname) {
        this.roomlbname = roomlbname;
    }

    public Timestamp getDtIndate() {
        return dtIndate;
    }

    public void setDtIndate(Timestamp dtIndate) {
        this.dtIndate = dtIndate;
    }

    public double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(double payPrice) {
        this.payPrice = payPrice;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getGuestname() {
        return guestname;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Roomqty{" +
                "regno='" + regno + '\'' +
                ", newDtOutdate=" + newDtOutdate +
                ", dtOutdate=" + dtOutdate +
                ", roomno='" + roomno + '\'' +
                ", roomqty='" + roomqty + '\'' +
                ", regPrice=" + regPrice +
                ", rstatus='" + rstatus + '\'' +
                ", roomlbname='" + roomlbname + '\'' +
                ", dtIndate=" + dtIndate +
                ", payPrice=" + payPrice +
                ", days=" + days +
                ", guestname='" + guestname + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
