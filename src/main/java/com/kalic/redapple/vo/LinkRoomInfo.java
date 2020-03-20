package com.kalic.redapple.vo;

/**
 * @author Kalic Li
 * @ClassName LinkRoomInfo
 * @Package com.kalic.redapple.vo
 * @Description 房间联房接受信息
 * @date 2020/3/9 21:09
 */
public class LinkRoomInfo {
    private String regno;
    private String roomno;
    private String linkno;
    private Integer linkid;

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

    public String getLinkno() {
        return linkno;
    }

    public void setLinkno(String linkno) {
        this.linkno = linkno;
    }

    public Integer getLinkid() {
        return linkid;
    }

    public void setLinkid(Integer linkid) {
        this.linkid = linkid;
    }

    @Override
    public String toString() {
        return "LinkRoomInfo{" +
                "regno='" + regno + '\'' +
                ", roomno='" + roomno + '\'' +
                ", linkno='" + linkno + '\'' +
                ", linkid=" + linkid +
                '}';
    }
}
