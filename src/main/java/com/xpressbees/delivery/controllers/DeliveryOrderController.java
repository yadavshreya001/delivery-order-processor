package com.xpressbees.delivery.controllers;

import com.xpressbees.delivery.models.DeliveryOrder;
import com.xpressbees.delivery.models.DeliveryStatus;
import com.xpressbees.delivery.services.DeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DeliveryOrderController {

    @Autowired
    private DeliveryOrderService service;

    @PostMapping("/orders")
    public DeliveryOrder addOrder(@RequestBody DeliveryOrder order) {
        return service.addOrder(order);
    }

    @GetMapping("/orders")
    public List<DeliveryOrder> getOrdersByStatus(@RequestParam DeliveryStatus status) {
        return service.getOrdersByStatus(status);
    }

    @GetMapping("/customers/top")
    public List<Object[]> getTopCustomers() {
        return service.getTopCustomers();
    }

    @GetMapping("/orders/status-count")
    public List<Object[]> getOrderStatusCount() {
        return service.getOrderStatusCount();
    }
}
