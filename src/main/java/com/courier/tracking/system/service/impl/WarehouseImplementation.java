package com.courier.tracking.system.service.impl;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.courier.tracking.system.entity.Warehouse;
import com.courier.tracking.system.repository.WarehouseRepository;
import com.courier.tracking.system.requestdto.WarehouseRequestDto;
import com.courier.tracking.system.responsedto.WarehouseResponseDto;
import com.courier.tracking.system.service.WarehouseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WarehouseImplementation implements WarehouseService{
	
	private final WarehouseRepository warehouseRepository;
	private final ModelMapper modelMapper;

	@Override
	public WarehouseResponseDto createWarehouse(WarehouseRequestDto warehouseRequestDto) {
		Warehouse warehouse = modelMapper.map(warehouseRequestDto, Warehouse.class);
		Warehouse savedWarehouse = warehouseRepository.save(warehouse);
		return modelMapper.map(savedWarehouse, WarehouseResponseDto.class);
	}

	@Override
	public List<WarehouseResponseDto> getAllWarehouse() {
		List<Warehouse> aList = warehouseRepository.findAll();
		if(aList.isEmpty()) {
			throw new IllegalArgumentException("No Warehouse found");
		}
		return aList.stream().map(val->modelMapper.map(val, WarehouseResponseDto.class)).toList();
	}

	@Override
	public WarehouseResponseDto getById(Long warehouseId) {
		Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow(()-> new IllegalArgumentException("No Warehouse found with Id" + warehouseId));
		return modelMapper.map(warehouse, WarehouseResponseDto.class);
	}

	@Override
	public List<WarehouseResponseDto> getByCapacity(Integer capacity) {
		List<Warehouse> warehouse = warehouseRepository.findByCapacity(capacity);
		if(warehouse.isEmpty()) {
			throw new IllegalArgumentException("No Warehouse with capacity" + capacity);
		}
	return warehouse.stream().map(val->modelMapper.map(val, WarehouseResponseDto.class)).toList();
	}

	@Override
	public List<WarehouseResponseDto> getByMinimumCapacity(Integer capacity) {
		List<Warehouse> warehouse = warehouseRepository.findByCapacityGreaterThanEqual(capacity);
			if(warehouse.isEmpty()) {
				throw new IllegalArgumentException("No Warehouse with capacity" + capacity);
			}
		return warehouse.stream().map(val->modelMapper.map(val, WarehouseResponseDto.class)).toList();
		
	}

	@Override
	public WarehouseResponseDto updateWarehouse(Long warehouseId, Map<String, Object> updates) {
		Warehouse warehouse = warehouseRepository.findById(warehouseId).
				orElseThrow(()-> new IllegalArgumentException("No Warehouse found with Id" + warehouseId));
		updates.forEach((field,value)->{
			 switch (field) {

             case "name" ->
                     warehouse.setName((String) value);
                     
                     //Capacity is a Integer, I have to convert String to Integer So First I will typecast it then use intValue() method 

             case "capacity" ->
                     warehouse.setCapacity(
                             ((Number) value).intValue()
                     );

             case "contactNumber" ->
                     warehouse.setContactNumber((String) value);

             default ->
                     throw new IllegalArgumentException(
                             "Invalid field: " + field
                     );
         }
		});
		 Warehouse updatedWarehouse =
	                warehouseRepository.save(warehouse);

	        return modelMapper.map(
	                updatedWarehouse,
	                WarehouseResponseDto.class
	        );
	}

	@Override
	public void deleteWarehouse(Long warehouseId) {
		Warehouse warehouse = warehouseRepository.findById(warehouseId)
				.orElseThrow(()-> new IllegalArgumentException("No Warehouse found with Id" + warehouseId));
		warehouseRepository.deleteById(warehouseId);
		
	}

}
