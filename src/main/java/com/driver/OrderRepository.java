package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {
    HashMap<String,Order> orderDb=new HashMap<>();
    HashMap<String, DeliveryPartner> deliverypartnerDb = new HashMap<>();
    HashMap<String, List<String>> partnerOrdersList=new HashMap<>();

    public void addOrder(Order order) {
        if(order!=null)
        orderDb.put(order.getId(),order);
    }

    public void addPartner(String partnerId) {
        deliverypartnerDb.put(partnerId,new DeliveryPartner(partnerId));
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        if(partnerOrdersList.containsKey(partnerId)) {
            List<String> list=partnerOrdersList.get(partnerId);
            list.add(orderId);
            partnerOrdersList.put(partnerId, list);
            deliverypartnerDb.get(partnerId).setNumberOfOrders(list.size());
        }
        else {
            List<String> list=new ArrayList<>();
            list.add(orderId);
            partnerOrdersList.put(partnerId,list);
            deliverypartnerDb.get(partnerId).setNumberOfOrders(list.size());
        }

    }

    public Order getOrder(String orderId) {
        if(orderDb.containsKey(orderId))
        return orderDb.get(orderId);
        return null;
    }

    public DeliveryPartner getPartner(String partnerId) {
        if(deliverypartnerDb.containsKey(partnerId))
            return deliverypartnerDb.get(partnerId);
        return null;
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        if(partnerOrdersList.containsKey(partnerId))
            return partnerOrdersList.get(partnerId).size();
        return 0;
    }

    public List<String> getOrdersIdByPartnerId(String partnerId) {
        List<String> orders=new ArrayList<>();
        if(partnerOrdersList.containsKey(partnerId))
            orders=partnerOrdersList.get(partnerId);
        return orders;
    }

    public List<String> getAllOrders() {
        List<String> orderIds=new ArrayList<>();
        for(String key:orderDb.keySet())
            orderIds.add(key);
         return orderIds;
    }

    public Integer getCountOfUnassignedOrders() {
       int total= orderDb.size();
       int assign=0;
        for(String s:partnerOrdersList.keySet())
        {
            assign+=partnerOrdersList.get(s).size();
        }
        return (total-assign);
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        int h=Integer.parseInt(time.substring(0,2));
        int  m=Integer.parseInt(time.substring(3));
        int t=h*60+m;
        int ans=0;
        for(String order:partnerOrdersList.get(partnerId))
        {
           if(orderDb.get(order).getDeliveryTime()>t)
               ans++;
        }
        return ans;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        int t=0;
        for(String order:partnerOrdersList.get(partnerId))
        {
            if(orderDb.get(order).getDeliveryTime()>t)
                t=orderDb.get(order).getDeliveryTime();
        }
        int h=t/60;
        int m=t%60;
        StringBuilder sb=new StringBuilder();
        if(h<10)sb.append('0');
        sb.append(h);
        sb.append(':');
        if(m<10)sb.append('0');
        sb.append(m);
        return sb.toString();
    }

    public void deletePartnerById(String partnerId) {
        if(deliverypartnerDb.containsKey(partnerId))
        deliverypartnerDb.remove(partnerId);
        if(partnerOrdersList.containsKey(partnerId))
            partnerOrdersList.remove(partnerId);
    }

    public void deleteOrderById(String orderId) {
        if(orderDb.containsKey(orderId))
            orderDb.remove(orderId);
        for(String partner:partnerOrdersList.keySet())
        {
            boolean found=false;
            for(String order:partnerOrdersList.get(partner))
            {
                if(order.equals(orderId)){
                    found=true;break;}
            }
            if(found)//if orderId found in certain partner,we remove that order from that partner
            {
                partnerOrdersList.get(partner).remove(orderId);
                int size=partnerOrdersList.get(partner).size();
                deliverypartnerDb.get(partner).setNumberOfOrders(size);
                break;
            }
        }
    }
}
