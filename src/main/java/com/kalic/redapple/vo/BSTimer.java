package com.kalic.redapple.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Kalic Li
 * @ClassName BSTimer
 * @Package com.kalic.redapple.vo
 * @Description 前端 营业中心 定时器中发送来的数据格式
 * @date 2020/2/16 15:34
 */
public class BSTimer implements Serializable {
    private String regno;
    private String bookingno;
    private String roomno;
    private String rstatus;
    private Timestamp dtIndate;
    private Timestamp dtOutdate;
    private Timestamp dtKeepdate;

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

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public String getRstatus() {
        return rstatus;
    }

    public void setRstatus(String rstatus) {
        this.rstatus = rstatus;
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

    public Timestamp getDtKeepdate() {
        return dtKeepdate;
    }

    public void setDtKeepdate(Timestamp dtKeepdate) {
        this.dtKeepdate = dtKeepdate;
    }

    @Override
    public String toString() {
        return "BSTimer{" +
                "regno='" + regno + '\'' +
                ", bookingno='" + bookingno + '\'' +
                ", roomno='" + roomno + '\'' +
                ", rstatus='" + rstatus + '\'' +
                ", dtIndate=" + dtIndate +
                ", dtOutdate=" + dtOutdate +
                ", dtKeepdate=" + dtKeepdate +
                '}';
    }
}
