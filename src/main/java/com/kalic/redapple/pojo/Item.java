package com.kalic.redapple.pojo;


public class Item {

  private String itemno;
  private String itemname;
  private double price;
  private long stock;


  public String getItemno() {
    return itemno;
  }

  public void setItemno(String itemno) {
    this.itemno = itemno;
  }


  public String getItemname() {
    return itemname;
  }

  public void setItemname(String itemname) {
    this.itemname = itemname;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public long getStock() {
    return stock;
  }

  public void setStock(long stock) {
    this.stock = stock;
  }

  @Override
  public String toString() {
    return "Item{" +
            "itemno='" + itemno + '\'' +
            ", itemname='" + itemname + '\'' +
            ", price=" + price +
            ", stock=" + stock +
            '}';
  }
}
