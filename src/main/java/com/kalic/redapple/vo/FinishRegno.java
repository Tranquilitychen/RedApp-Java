package com.kalic.redapple.vo;

import java.util.Arrays;
import java.util.List;

/**
 * @author Kalic Li
 * @ClassName FinishRegno
 * @Package com.kalic.redapple.vo
 * @Description TODO
 * @date 2020/3/11 21:13
 */
public class FinishRegno {
    private String regno;
    private String roomno;
    private String bookingno;
    private long guestno;
    private Integer[] flowids;
    private String sourcetype;
    private String operid;
    private String meno;
    private double totalSecurity;
    private double totalRegBill;

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
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

    public Integer[] getFlowids() {
        return flowids;
    }

    public void setFlowids(Integer[] flowids) {
        this.flowids = flowids;
    }

    public String getSourcetype() {
        return sourcetype;
    }

    public void setSourcetype(String sourcetype) {
        this.sourcetype = sourcetype;
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

    public double getTotalSecurity() {
        return totalSecurity;
    }

    public void setTotalSecurity(double totalSecurity) {
        this.totalSecurity = totalSecurity;
    }

    public double getTotalRegBill() {
        return totalRegBill;
    }

    public void setTotalRegBill(double totalRegBill) {
        this.totalRegBill = totalRegBill;
    }

    @Override
    public String toString() {
        return "FinishRegno{" +
                "regno='" + regno + '\'' +
                ", roomno='" + roomno + '\'' +
                ", bookingno='" + bookingno + '\'' +
                ", guestno=" + guestno +
                ", flowids=" + Arrays.toString(flowids) +
                ", sourcetype='" + sourcetype + '\'' +
                ", operid='" + operid + '\'' +
                ", meno='" + meno + '\'' +
                ", totalSecurity=" + totalSecurity +
                ", totalRegBill=" + totalRegBill +
                '}';
    }
}
