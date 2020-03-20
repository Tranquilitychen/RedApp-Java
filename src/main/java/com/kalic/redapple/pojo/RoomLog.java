package com.kalic.redapple.pojo;


public class RoomLog {

  private long flowid;
  private String roomno;
  private String regno;
  private String bookingno;
  private String logtype;
  private String logname;
  private String memo;
  private java.sql.Timestamp dtOper;
  private String operid;


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


  public String getBookingno() {
    return bookingno;
  }

  public void setBookingno(String bookingno) {
    this.bookingno = bookingno;
  }


  public String getLogtype() {
    return logtype;
  }

  public void setLogtype(String logtype) {
    this.logtype = logtype;
  }


  public String getLogname() {
    return logname;
  }

  public void setLogname(String logname) {
    this.logname = logname;
  }


  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
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

    @Override
    public String toString() {
        return "RoomLogMapper{" +
                "flowid=" + flowid +
                ", roomno='" + roomno + '\'' +
                ", regno='" + regno + '\'' +
                ", bookingno='" + bookingno + '\'' +
                ", logtype='" + logtype + '\'' +
                ", logname='" + logname + '\'' +
                ", memo='" + memo + '\'' +
                ", dtOper=" + dtOper +
                ", operid='" + operid + '\'' +
                '}';
    }
}
