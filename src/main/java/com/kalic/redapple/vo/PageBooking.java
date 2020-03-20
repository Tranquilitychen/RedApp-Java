package com.kalic.redapple.vo;

import java.sql.Timestamp;

/**
 * @author Kalic Li
 * @ClassName PageBooking
 * @Package com.kalic.redapple.vo
 * @Description 向前端页面传输的 Booking 预定信息
 * @date 2020/2/14 15:20
 */
public class PageBooking {
    private String roomno;
    private String roomlbno;
    private long groupid;
    private String sureflag;
    private String isgroup;
    private String guestname;
    private String guesttype;
    private String guestno;
    private String secrecyflag;
    private String vipno;
    private String groupname;
    private String groupleader;
    private String groupno;
    private long irooms;
    private java.sql.Timestamp dtIndate;
    private long days;
    private java.sql.Timestamp dtOutdate;
    private java.sql.Timestamp dtKeep;
    private String bookingtype;
    private long intype;
    private long cstatus;
    private String tel;
    private double discount;
    private double price;
    private String guestsource;
    private long irs;
    private long ifoods;
    private String idtypeno;
    private String idno;
    private String sex;
    private java.sql.Timestamp dtBirthday;
    private String countryno;
    private String areano;
    private String address;
    private String specialMemo;
    private String meno;
    private String securitytype;
    private double securityReal;
    private String paywayno;
    private String operid;
    private java.sql.Timestamp dtOper;

    @Override
    public String toString() {
        return "PageBooking{" +
                "roomno='" + roomno + '\'' +
                ", roomlbno='" + roomlbno + '\'' +
                ", groupid=" + groupid +
                ", sureflag='" + sureflag + '\'' +
                ", isgroup='" + isgroup + '\'' +
                ", guestname='" + guestname + '\'' +
                ", guesttype='" + guesttype + '\'' +
                ", guestno='" + guestno + '\'' +
                ", secrecyflag='" + secrecyflag + '\'' +
                ", vipno='" + vipno + '\'' +
                ", groupname='" + groupname + '\'' +
                ", groupleader='" + groupleader + '\'' +
                ", groupno='" + groupno + '\'' +
                ", irooms=" + irooms +
                ", dtIndate=" + dtIndate +
                ", days=" + days +
                ", dtOutdate=" + dtOutdate +
                ", dtKeep=" + dtKeep +
                ", bookingtype='" + bookingtype + '\'' +
                ", intype=" + intype +
                ", cstatus=" + cstatus +
                ", tel='" + tel + '\'' +
                ", discount=" + discount +
                ", price=" + price +
                ", guestsource='" + guestsource + '\'' +
                ", irs=" + irs +
                ", ifoods=" + ifoods +
                ", idtypeno='" + idtypeno + '\'' +
                ", idno='" + idno + '\'' +
                ", sex='" + sex + '\'' +
                ", dtBirthday=" + dtBirthday +
                ", countryno='" + countryno + '\'' +
                ", areano='" + areano + '\'' +
                ", address='" + address + '\'' +
                ", specialMemo='" + specialMemo + '\'' +
                ", meno='" + meno + '\'' +
                ", securitytype='" + securitytype + '\'' +
                ", securityReal=" + securityReal +
                ", paywayno='" + paywayno + '\'' +
                ", operid='" + operid + '\'' +
                ", dtOper=" + dtOper +
                '}';
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

    public long getGroupid() {
        return groupid;
    }

    public void setGroupid(long groupid) {
        this.groupid = groupid;
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

    public String getGuestname() {
        return guestname;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }

    public String getGuesttype() {
        return guesttype;
    }

    public void setGuesttype(String guesttype) {
        this.guesttype = guesttype;
    }

    public String getGuestno() {
        return guestno;
    }

    public void setGuestno(String guestno) {
        this.guestno = guestno;
    }

    public String getSecrecyflag() {
        return secrecyflag;
    }

    public void setSecrecyflag(String secrecyflag) {
        this.secrecyflag = secrecyflag;
    }

    public String getVipno() {
        return vipno;
    }

    public void setVipno(String vipno) {
        this.vipno = vipno;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
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

    public long getIrooms() {
        return irooms;
    }

    public void setIrooms(long irooms) {
        this.irooms = irooms;
    }

    public Timestamp getDtIndate() {
        return dtIndate;
    }

    public void setDtIndate(Timestamp dtIndate) {
        this.dtIndate = dtIndate;
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
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

    public String getBookingtype() {
        return bookingtype;
    }

    public void setBookingtype(String bookingtype) {
        this.bookingtype = bookingtype;
    }

    public long getIntype() {
        return intype;
    }

    public void setIntype(long intype) {
        this.intype = intype;
    }

    public long getCstatus() {
        return cstatus;
    }

    public void setCstatus(long cstatus) {
        this.cstatus = cstatus;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getGuestsource() {
        return guestsource;
    }

    public void setGuestsource(String guestsource) {
        this.guestsource = guestsource;
    }

    public long getIrs() {
        return irs;
    }

    public void setIrs(long irs) {
        this.irs = irs;
    }

    public long getIfoods() {
        return ifoods;
    }

    public void setIfoods(long ifoods) {
        this.ifoods = ifoods;
    }

    public String getIdtypeno() {
        return idtypeno;
    }

    public void setIdtypeno(String idtypeno) {
        this.idtypeno = idtypeno;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Timestamp getDtBirthday() {
        return dtBirthday;
    }

    public void setDtBirthday(Timestamp dtBirthday) {
        this.dtBirthday = dtBirthday;
    }

    public String getCountryno() {
        return countryno;
    }

    public void setCountryno(String countryno) {
        this.countryno = countryno;
    }

    public String getAreano() {
        return areano;
    }

    public void setAreano(String areano) {
        this.areano = areano;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPaywayno() {
        return paywayno;
    }

    public void setPaywayno(String paywayno) {
        this.paywayno = paywayno;
    }

    public String getOperid() {
        return operid;
    }

    public void setOperid(String operid) {
        this.operid = operid;
    }

    public Timestamp getDtOper() {
        return dtOper;
    }

    public void setDtOper(Timestamp dtOper) {
        this.dtOper = dtOper;
    }
}
