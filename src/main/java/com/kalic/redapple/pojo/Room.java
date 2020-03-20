package com.kalic.redapple.pojo;


public class Room {

  private String roomno;
  private String roomname;
  private String roomlbno;
  private String floorno;
  private long beds;
  private String tel;
  private String rstatus;
  private String isditry;
  private String isown;
  private String stopflag;
  private String clockflag;
  private String clockOperid;
  private String dtLock;
  private String buildno;
  private String memoTs;
  private String meno;
  private String doorlockid;
  private String doorroomid;
  private String bookingno;
  private String regno;
  private String ownno;
  private String stopno;


  public String getRoomno() {
    return roomno;
  }

  public void setRoomno(String roomno) {
    this.roomno = roomno;
  }


  public String getRoomname() {
    return roomname;
  }

  public void setRoomname(String roomname) {
    this.roomname = roomname;
  }


  public String getRoomlbno() {
    return roomlbno;
  }

  public void setRoomlbno(String roomlbno) {
    this.roomlbno = roomlbno;
  }


  public String getFloorno() {
    return floorno;
  }

  public void setFloorno(String floorno) {
    this.floorno = floorno;
  }


  public long getBeds() {
    return beds;
  }

  public void setBeds(long beds) {
    this.beds = beds;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getRstatus() {
    return rstatus;
  }

  public void setRstatus(String rstatus) {
    this.rstatus = rstatus;
  }


  public String getIsditry() {
    return isditry;
  }

  public void setIsditry(String isditry) {
    this.isditry = isditry;
  }


  public String getIsown() {
    return isown;
  }

  public void setIsown(String isown) {
    this.isown = isown;
  }


  public String getStopflag() {
    return stopflag;
  }

  public void setStopflag(String stopflag) {
    this.stopflag = stopflag;
  }


  public String getClockflag() {
    return clockflag;
  }

  public void setClockflag(String clockflag) {
    this.clockflag = clockflag;
  }


  public String getClockOperid() {
    return clockOperid;
  }

  public void setClockOperid(String clockOperid) {
    this.clockOperid = clockOperid;
  }


  public String getDtLock() {
    return dtLock;
  }

  public void setDtLock(String dtLock) {
    this.dtLock = dtLock;
  }


  public String getBuildno() {
    return buildno;
  }

  public void setBuildno(String buildno) {
    this.buildno = buildno;
  }


  public String getMemoTs() {
    return memoTs;
  }

  public void setMemoTs(String memoTs) {
    this.memoTs = memoTs;
  }


  public String getMeno() {
    return meno;
  }

  public void setMeno(String meno) {
    this.meno = meno;
  }


  public String getDoorlockid() {
    return doorlockid;
  }

  public void setDoorlockid(String doorlockid) {
    this.doorlockid = doorlockid;
  }


  public String getDoorroomid() {
    return doorroomid;
  }

  public void setDoorroomid(String doorroomid) {
    this.doorroomid = doorroomid;
  }


  public String getBookingno() {
    return bookingno;
  }

  public void setBookingno(String bookingno) {
    this.bookingno = bookingno;
  }


  public String getRegno() {
    return regno;
  }

  public void setRegno(String regno) {
    this.regno = regno;
  }


  public String getOwnno() {
    return ownno;
  }

  public void setOwnno(String ownno) {
    this.ownno = ownno;
  }


  public String getStopno() {
    return stopno;
  }

  public void setStopno(String stopno) {
    this.stopno = stopno;
  }

  @Override
  public String toString() {
    return "Room{" +
            "roomno='" + roomno + '\'' +
            ", roomname='" + roomname + '\'' +
            ", roomlbno='" + roomlbno + '\'' +
            ", floorno='" + floorno + '\'' +
            ", beds=" + beds +
            ", tel='" + tel + '\'' +
            ", rstatus='" + rstatus + '\'' +
            ", isditry='" + isditry + '\'' +
            ", isown='" + isown + '\'' +
            ", stopflag='" + stopflag + '\'' +
            ", clockflag='" + clockflag + '\'' +
            ", clockOperid='" + clockOperid + '\'' +
            ", dtLock='" + dtLock + '\'' +
            ", buildno='" + buildno + '\'' +
            ", memoTs='" + memoTs + '\'' +
            ", meno='" + meno + '\'' +
            ", doorlockid='" + doorlockid + '\'' +
            ", doorroomid='" + doorroomid + '\'' +
            ", bookingno='" + bookingno + '\'' +
            ", regno='" + regno + '\'' +
            ", ownno='" + ownno + '\'' +
            ", stopno='" + stopno + '\'' +
            '}';
  }
}
