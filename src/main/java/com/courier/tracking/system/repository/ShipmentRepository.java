package com.courier.tracking.system.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courier.tracking.system.entity.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

	Optional<Shipment> findByTrackingNumber(String trackingNumber);

	List<Shipment> findByCustomerCustomerId(Long customerId);

	List<Shipment> findByWarehouseWarehouseId(Long warehouseId);

	List<Shipment> findByDeliveryAgentDeliveryAgentId(Long deliveryAgentId);

	List<Shipment> findBySourceAndDestination(String source, String destination);

	List<Shipment> findByDeliveryDate(LocalDateTime deliveryDate);

}
