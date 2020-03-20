package com.kalic.redapple.vo;

import java.sql.Timestamp;

/**
 * @author Kalic Li
 * @ClassName PageCancelBooking
 * @Package com.kalic.redapple.vo
 * @Description 取消预定所需的包装类信息,需要再封装
 * @date 2020/2/18 20:59
 */
public class PageCancelBooking {
    private String bookingno;
    private String guestname;
    private String roomno;
    private long vipno;
    private String roomlbname;
    private long rstatus;
    private double securityReal;
    private java.sql.Timestamp dtIndate;
    private java.sql.Timestamp dtOutdate;
    private java.sql.Timestamp dtKeep;
    private String linkno;
    private String groupno;

    public String getBookingno() {
        return bookingno;
    }

    public void setBookingno(String bookingno) {
        this.bookingno = bookingno;
    }

    public String getGuestname() {
        return guestname;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public long getVipno() {
        return vipno;
    }

    public void setVipno(long vipno) {
        this.vipno = vipno;
    }

    public String getRoomlbname() {
        return roomlbname;
    }

    public void setRoomlbname(String roomlbname) {
        this.roomlbname = roomlbname;
    }

    public long getRstatus() {
        return rstatus;
    }

    public void setRstatus(long rstatus) {
        this.rstatus = rstatus;
    }

    public double getSecurityReal() {
        return securityReal;
    }

    public void setSecurityReal(double securityReal) {
        this.securityReal = securityReal;
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

    public Timestamp getDtKeep() {
        return dtKeep;
    }

    public void setDtKeep(Timestamp dtKeep) {
        this.dtKeep = dtKeep;
    }

    public String getLinkno() {
        return linkno;
    }

    public void setLinkno(String linkno) {
        this.linkno = linkno;
    }

    public String getGroupno() {
        return groupno;
    }

    public void setGroupno(String groupno) {
        this.groupno = groupno;
    }

    @Override
    public String toString() {
        return "PageCancelBooking{" +
                "bookingno='" + bookingno + '\'' +
                ", guestname='" + guestname + '\'' +
                ", roomno='" + roomno + '\'' +
                ", vipno=" + vipno +
                ", roomlbname='" + roomlbname + '\'' +
                ", rstatus=" + rstatus +
                ", securityReal=" + securityReal +
                ", dtIndate=" + dtIndate +
                ", dtOutdate=" + dtOutdate +
                ", dtKeep=" + dtKeep +
                ", linkno='" + linkno + '\'' +
                ", groupno='" + groupno + '\'' +
                '}';
    }
}
