package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepo;

    public void addOrder(Order order) {
        orderRepo.addOrder(order);
    }

    public void addPartner(String partnerId) {
        orderRepo.addPartner(partnerId);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        orderRepo.addOrderPartnerPair(orderId,partnerId);
    }

    public Order getOrder(String orderId) {
        return orderRepo.getOrder(orderId);
    }

    public DeliveryPartner getPartner(String partnerId) {
        return orderRepo.getPartner(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        return orderRepo.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersIdByPartnerId(String partnerId) {
        return orderRepo.getOrdersIdByPartnerId(partnerId);
    }

    public List<String> getAllOrders() {
        return orderRepo.getAllOrders();
    }

    public Integer getCountOfUnassignedOrders() {
       return orderRepo.getCountOfUnassignedOrders();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        return orderRepo.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        return orderRepo.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public void deletePartnerById(String partnerId) {
        orderRepo.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId) {
        orderRepo.deleteOrderById(orderId);
    }
}
