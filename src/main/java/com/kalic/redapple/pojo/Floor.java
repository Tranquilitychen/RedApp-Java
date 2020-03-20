package com.kalic.redapple.pojo;


public class Floor {

  private String floorno;
  private String floorname;


  public String getFloorno() {
    return floorno;
  }

  public void setFloorno(String floorno) {
    this.floorno = floorno;
  }


  public String getFloorname() {
    return floorname;
  }

  public void setFloorname(String floorname) {
    this.floorname = floorname;
  }

  @Override
  public String toString() {
    return "Floor{" +
            "floorno='" + floorno + '\'' +
            ", floorname='" + floorname + '\'' +
            '}';
  }
}
