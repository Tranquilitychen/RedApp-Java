package com.kalic.redapple.vo;

/**
 * @author Kalic Li
 * @ClassName FinishReg
 * @Package com.kalic.redapple.vo
 * @Description TODO
 * @date 2020/2/20 20:08
 */
public class FinishReg {
    private String regno;
    private String roomno;
    private String guestname;
    private double price;
    private double mustPrice;
    private double securityReal;
    private double discount;
    private double amountReceviable;
    private double change;
    private String paymeno;

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

    public String getGuestname() {
        return guestname;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMustPrice() {
        return mustPrice;
    }

    public void setMustPrice(double mustPrice) {
        this.mustPrice = mustPrice;
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

    public double getAmountReceviable() {
        return amountReceviable;
    }

    public void setAmountReceviable(double amountReceviable) {
        this.amountReceviable = amountReceviable;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public String getPaymeno() {
        return paymeno;
    }

    public void setPaymeno(String paymeno) {
        this.paymeno = paymeno;
    }

    @Override
    public String toString() {
        return "FinishReg{" +
                "regno='" + regno + '\'' +
                ", roomno='" + roomno + '\'' +
                ", guestname='" + guestname + '\'' +
                ", price=" + price +
                ", mustPrice=" + mustPrice +
                ", securityReal=" + securityReal +
                ", discount=" + discount +
                ", amountReceviable=" + amountReceviable +
                ", change=" + change +
                ", paymeno='" + paymeno + '\'' +
                '}';
    }
}
