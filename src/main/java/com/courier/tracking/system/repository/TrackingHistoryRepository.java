package com.courier.tracking.system.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courier.tracking.system.entity.TrackingHistory;

@Repository
public interface TrackingHistoryRepository extends JpaRepository<TrackingHistory, Long>{
	
	List<TrackingHistory> findByShipment_TrackingNumber(String trackingNumber);

}
