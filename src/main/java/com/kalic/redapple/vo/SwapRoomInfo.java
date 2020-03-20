package com.kalic.redapple.vo;

import java.sql.Timestamp;

/**
 * @author Kalic Li
 * @ClassName SwapRoomInfo
 * @Package com.kalic.redapple.vo
 * @Description TODO
 * @date 2020/3/11 12:57
 */
public class SwapRoomInfo {
    private long flowid;
    private String oldRoomno;
    private String newRoomno;
    private String regno;
    private double newRegPrice;
    private boolean newBillFlag;
    private Integer oldBillNum;
    private Integer newBillNum;
    private double updBillTotalPrice;
    private double insBillTotalPrice;
    private java.sql.Timestamp dtIndate;
    private java.sql.Timestamp dtOutdate;
    private java.sql.Timestamp dtOper;
    private String operid;

    public long getFlowid() {
        return flowid;
    }

    public void setFlowid(long flowid) {
        this.flowid = flowid;
    }

    public Timestamp getDtOper() {
        return dtOper;
    }

    public void setDtOper(Timestamp dtOper) {
        this.dtOper = dtOper;
    }

    public String getOperid() {
        return operid;
    }

    public void setOperid(String operid) {
        this.operid = operid;
    }

    public double getUpdBillTotalPrice() {
        return updBillTotalPrice;
    }

    public void setUpdBillTotalPrice(double updBillTotalPrice) {
        this.updBillTotalPrice = updBillTotalPrice;
    }

    public double getInsBillTotalPrice() {
        return insBillTotalPrice;
    }

    public void setInsBillTotalPrice(double insBillTotalPrice) {
        this.insBillTotalPrice = insBillTotalPrice;
    }

    public String getOldRoomno() {
        return oldRoomno;
    }

    public void setOldRoomno(String oldRoomno) {
        this.oldRoomno = oldRoomno;
    }

    public String getNewRoomno() {
        return newRoomno;
    }

    public void setNewRoomno(String newRoomno) {
        this.newRoomno = newRoomno;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public double getNewRegPrice() {
        return newRegPrice;
    }

    public void setNewRegPrice(double newRegPrice) {
        this.newRegPrice = newRegPrice;
    }

    public boolean getNewBillFlag() {
        return newBillFlag;
    }

    public void setNewBillFlag(boolean newBillFlag) {
        this.newBillFlag = newBillFlag;
    }

    public Integer getOldBillNum() {
        return oldBillNum;
    }

    public void setOldBillNum(Integer oldBillNum) {
        this.oldBillNum = oldBillNum;
    }

    public Integer getNewBillNum() {
        return newBillNum;
    }

    public void setNewBillNum(Integer newBillNum) {
        this.newBillNum = newBillNum;
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

    @Override
    public String toString() {
        return "SwapRoomInfo{" +
                "flowid='" + flowid + '\'' +
                ", oldRoomno='" + oldRoomno + '\'' +
                ", newRoomno='" + newRoomno + '\'' +
                ", regno='" + regno + '\'' +
                ", newRegPrice=" + newRegPrice +
                ", newBillFlag=" + newBillFlag +
                ", oldBillNum=" + oldBillNum +
                ", newBillNum=" + newBillNum +
                ", updBillTotalPrice=" + updBillTotalPrice +
                ", insBillTotalPrice=" + insBillTotalPrice +
                ", dtIndate=" + dtIndate +
                ", dtOutdate=" + dtOutdate +
                ", dtOper=" + dtOper +
                ", operid='" + operid + '\'' +
                '}';
    }
}
