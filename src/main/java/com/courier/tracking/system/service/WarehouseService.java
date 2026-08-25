package com.courier.tracking.system.service;

import java.util.List;
import java.util.Map;

import com.courier.tracking.system.requestdto.WarehouseRequestDto;
import com.courier.tracking.system.responsedto.WarehouseResponseDto;

public interface WarehouseService {
	
	WarehouseResponseDto createWarehouse(WarehouseRequestDto warehouseRequestDto);
	
	List<WarehouseResponseDto> getAllWarehouse();
	
	WarehouseResponseDto getById(Long warehouseId);
	
	List<WarehouseResponseDto> getByCapacity(Integer capacity);
	
	List<WarehouseResponseDto> getByMinimumCapacity(Integer capacity);
	
	WarehouseResponseDto updateWarehouse(Long warehouseId,Map<String, Object> updates);
	
	void deleteWarehouse(Long warehouseId);

}
