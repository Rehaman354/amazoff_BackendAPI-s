package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
        this.id=id;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        //the format we get is HH:MM
      int h=Integer.parseInt(deliveryTime.substring(0,2));
        int  m=Integer.parseInt(deliveryTime.substring(3));
        int time=h*60+m;
        this.deliveryTime=time;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
