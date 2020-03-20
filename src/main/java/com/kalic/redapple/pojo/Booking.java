package com.kalic.redapple.pojo;


public class Booking {

  private String bookingno;
  private String regno;
  private String roomno;
  private String roomlbno;
  private String linkno;
  private long linkid;
  private String sureflag;
  private String isgroup;
  private long guestno;
  private long vipno;
  private String groupleader;
  private String groupno;
  private java.sql.Timestamp dtIndate;
  private java.sql.Timestamp dtOutdate;
  private java.sql.Timestamp dtKeep;
  private long cstatus;
  private long days;
  private long irs;
  private String guestsource;
  private String bookingtype;
  private String specialMemo;
  private String meno;
  private String operid;
  private java.sql.Timestamp dtOper;
  private String securitytype;
  private double securityReal;
  private double discount;
  private double price;
  private String paywayno;
  private java.sql.Timestamp dtCancel;
  private String cancelMeno;
  private java.sql.Timestamp dtSure;


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


  public String getRoomno() {
    return roomno;
  }

  public void setRoomno(String roomno) {
    this.roomno = roomno;
  }


  public String getRoomlbno() {
    return roomlbno;
  }

  public void setRoomlbno(String roomlbno) {
    this.roomlbno = roomlbno;
  }


  public String getLinkno() {
    return linkno;
  }

  public void setLinkno(String linkno) {
    this.linkno = linkno;
  }


  public long getLinkid() {
    return linkid;
  }

  public void setLinkid(long linkid) {
    this.linkid = linkid;
  }


  public String getSureflag() {
    return sureflag;
  }

  public void setSureflag(String sureflag) {
    this.sureflag = sureflag;
  }


  public String getIsgroup() {
    return isgroup;
  }

  public void setIsgroup(String isgroup) {
    this.isgroup = isgroup;
  }


  public long getGuestno() {
    return guestno;
  }

  public void setGuestno(long guestno) {
    this.guestno = guestno;
  }


  public long getVipno() {
    return vipno;
  }

  public void setVipno(long vipno) {
    this.vipno = vipno;
  }


  public String getGroupleader() {
    return groupleader;
  }

  public void setGroupleader(String groupleader) {
    this.groupleader = groupleader;
  }


  public String getGroupno() {
    return groupno;
  }

  public void setGroupno(String groupno) {
    this.groupno = groupno;
  }


  public java.sql.Timestamp getDtIndate() {
    return dtIndate;
  }

  public void setDtIndate(java.sql.Timestamp dtIndate) {
    this.dtIndate = dtIndate;
  }


  public java.sql.Timestamp getDtOutdate() {
    return dtOutdate;
  }

  public void setDtOutdate(java.sql.Timestamp dtOutdate) {
    this.dtOutdate = dtOutdate;
  }


  public java.sql.Timestamp getDtKeep() {
    return dtKeep;
  }

  public void setDtKeep(java.sql.Timestamp dtKeep) {
    this.dtKeep = dtKeep;
  }


  public long getCstatus() {
    return cstatus;
  }

  public void setCstatus(long cstatus) {
    this.cstatus = cstatus;
  }


  public long getDays() {
    return days;
  }

  public void setDays(long days) {
    this.days = days;
  }


  public long getIrs() {
    return irs;
  }

  public void setIrs(long irs) {
    this.irs = irs;
  }


  public String getGuestsource() {
    return guestsource;
  }

  public void setGuestsource(String guestsource) {
    this.guestsource = guestsource;
  }


  public String getBookingtype() {
    return bookingtype;
  }

  public void setBookingtype(String bookingtype) {
    this.bookingtype = bookingtype;
  }


  public String getSpecialMemo() {
    return specialMemo;
  }

  public void setSpecialMemo(String specialMemo) {
    this.specialMemo = specialMemo;
  }


  public String getMeno() {
    return meno;
  }

  public void setMeno(String meno) {
    this.meno = meno;
  }


  public String getOperid() {
    return operid;
  }

  public void setOperid(String operid) {
    this.operid = operid;
  }


  public java.sql.Timestamp getDtOper() {
    return dtOper;
  }

  public void setDtOper(java.sql.Timestamp dtOper) {
    this.dtOper = dtOper;
  }


  public String getSecuritytype() {
    return securitytype;
  }

  public void setSecuritytype(String securitytype) {
    this.securitytype = securitytype;
  }


  public double getSecurityReal() {
    return securityReal;
  }

  public void setSecurityReal(double securityReal) {
    this.securityReal = securityReal;
  }


  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public String getPaywayno() {
    return paywayno;
  }

  public void setPaywayno(String paywayno) {
    this.paywayno = paywayno;
  }


  public java.sql.Timestamp getDtCancel() {
    return dtCancel;
  }

  public void setDtCancel(java.sql.Timestamp dtCancel) {
    this.dtCancel = dtCancel;
  }


  public String getCancelMeno() {
    return cancelMeno;
  }

  public void setCancelMeno(String cancelMeno) {
    this.cancelMeno = cancelMeno;
  }


  public java.sql.Timestamp getDtSure() {
    return dtSure;
  }

  public void setDtSure(java.sql.Timestamp dtSure) {
    this.dtSure = dtSure;
  }

  @Override
  public String toString() {
    return "Booking{" +
            "bookingno='" + bookingno + '\'' +
            ", regno='" + regno + '\'' +
            ", roomno='" + roomno + '\'' +
            ", roomlbno='" + roomlbno + '\'' +
            ", linkno='" + linkno + '\'' +
            ", linkid=" + linkid +
            ", sureflag='" + sureflag + '\'' +
            ", isgroup='" + isgroup + '\'' +
            ", guestno=" + guestno +
            ", vipno=" + vipno +
            ", groupleader='" + groupleader + '\'' +
            ", groupno='" + groupno + '\'' +
            ", dtIndate=" + dtIndate +
            ", dtOutdate=" + dtOutdate +
            ", dtKeep=" + dtKeep +
            ", cstatus=" + cstatus +
            ", days=" + days +
            ", irs=" + irs +
            ", guestsource='" + guestsource + '\'' +
            ", bookingtype='" + bookingtype + '\'' +
            ", specialMemo='" + specialMemo + '\'' +
            ", meno='" + meno + '\'' +
            ", operid='" + operid + '\'' +
            ", dtOper=" + dtOper +
            ", securitytype='" + securitytype + '\'' +
            ", securityReal=" + securityReal +
            ", discount=" + discount +
            ", price=" + price +
            ", paywayno='" + paywayno + '\'' +
            ", dtCancel=" + dtCancel +
            ", cancelMeno='" + cancelMeno + '\'' +
            ", dtSure=" + dtSure +
            '}';
  }
}
