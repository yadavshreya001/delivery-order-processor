package com.xpressbees.delivery.services;

import com.xpressbees.delivery.models.DeliveryOrder;
import com.xpressbees.delivery.models.DeliveryStatus;
import com.xpressbees.delivery.repositories.DeliveryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
public class DeliveryOrderService {
    @Autowired
    private DeliveryOrderRepository repository;

    @Autowired
    private ExecutorService executorService;

    public DeliveryOrder addOrder(DeliveryOrder order) {
        order.setCreatedAt(LocalDateTime.now());
        order.setDeliveryStatus(DeliveryStatus.PENDING);
        return repository.save(order);
    }

    public List<DeliveryOrder> getOrdersByStatus(DeliveryStatus status) {
        return repository.findByDeliveryStatus(status);
    }

    public List<Object[]> getTopCustomers() {
        return repository.findTopCustomers();
    }

    public List<Object[]> getOrderStatusCount() {
        return repository.countOrdersByStatus();
    }

    public void processPendingOrders() {
        List<DeliveryOrder> pendingOrders = repository.findByDeliveryStatus(DeliveryStatus.PENDING);
        for (DeliveryOrder order : pendingOrders) {
            executorService.submit(() -> processOrder(order));
        }
    }

    private synchronized void processOrder(DeliveryOrder order) {
        order.setDeliveryStatus(DeliveryStatus.IN_PROGRESS);
        repository.save(order);
        try {
            Thread.sleep(2000); // Simulating delivery time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        order.setDeliveryStatus(DeliveryStatus.DELIVERED);
        repository.save(order);
    }

}
