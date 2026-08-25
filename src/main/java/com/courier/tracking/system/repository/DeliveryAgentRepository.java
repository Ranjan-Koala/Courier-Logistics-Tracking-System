package com.courier.tracking.system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courier.tracking.system.entity.DeliveryAgent;

@Repository
public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Long>{
	
	Optional<DeliveryAgent> findByVehicleNumber(String vehicleNumber);
	
	Optional<DeliveryAgent> findByDeliveryPhone(String deliveryPhone);
	
	List<DeliveryAgent> findByRatingGreaterThanEqual(Double rating);
	

}
