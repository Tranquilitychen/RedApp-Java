package com.kalic.redapple.pojo;


public class Roomlb {

  private String roomlbno;
  private String roomlbname;
  private String subname;
  private long beds;
  private long breakfasts;
  private String longroomflag;
  private String timeroomflag;
  private double price;
  private double longprice;
  private double timeprice;
  private double weekendprice;
  private double holidayprice;
  private double timesoutTimes;
  private double itmesoutPrice;
  private double timesHourNum;
  private String chalfHourflag;
  private double nlcfprice;


  public String getRoomlbno() {
    return roomlbno;
  }

  public void setRoomlbno(String roomlbno) {
    this.roomlbno = roomlbno;
  }


  public String getRoomlbname() {
    return roomlbname;
  }

  public void setRoomlbname(String roomlbname) {
    this.roomlbname = roomlbname;
  }


  public String getSubname() {
    return subname;
  }

  public void setSubname(String subname) {
    this.subname = subname;
  }


  public long getBeds() {
    return beds;
  }

  public void setBeds(long beds) {
    this.beds = beds;
  }


  public long getBreakfasts() {
    return breakfasts;
  }

  public void setBreakfasts(long breakfasts) {
    this.breakfasts = breakfasts;
  }


  public String getLongroomflag() {
    return longroomflag;
  }

  public void setLongroomflag(String longroomflag) {
    this.longroomflag = longroomflag;
  }


  public String getTimeroomflag() {
    return timeroomflag;
  }

  public void setTimeroomflag(String timeroomflag) {
    this.timeroomflag = timeroomflag;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public double getLongprice() {
    return longprice;
  }

  public void setLongprice(double longprice) {
    this.longprice = longprice;
  }


  public double getTimeprice() {
    return timeprice;
  }

  public void setTimeprice(double timeprice) {
    this.timeprice = timeprice;
  }


  public double getWeekendprice() {
    return weekendprice;
  }

  public void setWeekendprice(double weekendprice) {
    this.weekendprice = weekendprice;
  }


  public double getHolidayprice() {
    return holidayprice;
  }

  public void setHolidayprice(double holidayprice) {
    this.holidayprice = holidayprice;
  }


  public double getTimesoutTimes() {
    return timesoutTimes;
  }

  public void setTimesoutTimes(double timesoutTimes) {
    this.timesoutTimes = timesoutTimes;
  }


  public double getItmesoutPrice() {
    return itmesoutPrice;
  }

  public void setItmesoutPrice(double itmesoutPrice) {
    this.itmesoutPrice = itmesoutPrice;
  }


  public double getTimesHourNum() {
    return timesHourNum;
  }

  public void setTimesHourNum(double timesHourNum) {
    this.timesHourNum = timesHourNum;
  }


  public String getChalfHourflag() {
    return chalfHourflag;
  }

  public void setChalfHourflag(String chalfHourflag) {
    this.chalfHourflag = chalfHourflag;
  }


  public double getNlcfprice() {
    return nlcfprice;
  }

  public void setNlcfprice(double nlcfprice) {
    this.nlcfprice = nlcfprice;
  }

  @Override
  public String toString() {
    return "Roomlb{" +
            "roomlbno='" + roomlbno + '\'' +
            ", roomlbname='" + roomlbname + '\'' +
            ", subname='" + subname + '\'' +
            ", beds=" + beds +
            ", breakfasts=" + breakfasts +
            ", longroomflag='" + longroomflag + '\'' +
            ", timeroomflag='" + timeroomflag + '\'' +
            ", price=" + price +
            ", longprice=" + longprice +
            ", timeprice=" + timeprice +
            ", weekendprice=" + weekendprice +
            ", holidayprice=" + holidayprice +
            ", timesoutTimes=" + timesoutTimes +
            ", itmesoutPrice=" + itmesoutPrice +
            ", timesHourNum=" + timesHourNum +
            ", chalfHourflag='" + chalfHourflag + '\'' +
            ", nlcfprice=" + nlcfprice +
            '}';
  }
}
