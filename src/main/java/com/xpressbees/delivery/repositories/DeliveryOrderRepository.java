package com.xpressbees.delivery.repositories;

import com.xpressbees.delivery.models.DeliveryOrder;
import com.xpressbees.delivery.models.DeliveryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Integer> {
    List<DeliveryOrder> findByDeliveryStatus(DeliveryStatus deliveryStatus);

    @Query("SELECT d.customerName, COUNT(d) as count FROM DeliveryOrder d WHERE d.deliveryStatus = 'DELIVERED' GROUP BY d.customerName ORDER BY count DESC")
    List<Object[]> findTopCustomers();

    @Query("SELECT d.deliveryStatus, COUNT(d) FROM DeliveryOrder d GROUP BY d.deliveryStatus")
    List<Object[]> countOrdersByStatus();
}