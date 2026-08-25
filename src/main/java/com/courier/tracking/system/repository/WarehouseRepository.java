package com.courier.tracking.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courier.tracking.system.entity.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long>{
	
	List<Warehouse> findByCapacity(Integer capacity);
	
	List<Warehouse> findByCapacityGreaterThanEqual(Integer capacity);

}
