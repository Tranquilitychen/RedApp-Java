package com.kalic.redapple.vo;

import com.kalic.redapple.pojo.*;

import java.util.List;

/**
 * @author Kalic Li
 * @ClassName AllData
 * @Package com.kalic.redapple.vo
 * @Description 全体数据集
 * @date 2020/2/14 20:28
 */
public class AllData {
    private Room room;
    private Roomlb roomlb;
    private Reg reg;
    private Booking booking;
    private Guest guest;
    private List<RegBill> regBill;
    private Floor floor;
    private List<PayDetailed> payDetaileds;

    public List<PayDetailed> getPayDetaileds() {
        return payDetaileds;
    }

    public void setPayDetaileds(List<PayDetailed> payDetaileds) {
        this.payDetaileds = payDetaileds;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Roomlb getRoomlb() {
        return roomlb;
    }

    public void setRoomlb(Roomlb roomlb) {
        this.roomlb = roomlb;
    }

    public Reg getReg() {
        return reg;
    }

    public void setReg(Reg reg) {
        this.reg = reg;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public List<RegBill> getRegBill() {
        return regBill;
    }

    public void setRegBill(List<RegBill> regBill) {
        this.regBill = regBill;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "AllData{" +
                "room=" + room +
                ", roomlb=" + roomlb +
                ", reg=" + reg +
                ", booking=" + booking +
                ", guest=" + guest +
                ", regBill=" + regBill +
                ", floor=" + floor +
                ", payDetaileds=" + payDetaileds +
                '}';
    }
}
