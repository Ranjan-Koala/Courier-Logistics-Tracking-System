package com.courier.tracking.system.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.courier.tracking.system.requestdto.PackageRequestDto;
import com.courier.tracking.system.requestdto.PaymentRequestDto;
import com.courier.tracking.system.requestdto.ShipmentRequestDto;
import com.courier.tracking.system.requestdto.TrackingHistoryRequestDto;
import com.courier.tracking.system.responsedto.ShipmentResponseDto;

public interface ShipmentService {

	ShipmentResponseDto createShipment(ShipmentRequestDto shipmentRequestDto, PackageRequestDto packageRequestDto,
			PaymentRequestDto paymentRequestDto, TrackingHistoryRequestDto trackingHistoryRequestDto);
	
	List<ShipmentResponseDto> getAllShipment();
	
	ShipmentResponseDto getById(Long shipmentId);
	
	ShipmentResponseDto getByTrackingNumber(String trackingNumber);
	
	ShipmentResponseDto updateShipmentStatus(Long shipmentId,Map<String, Object> updates);
	
	ShipmentResponseDto assignDeliveryAgent(Long shipmentId,Long agentId);
	
	ShipmentResponseDto assignWarehouse(Long shipmentId,Long warehouseId);
	
	void deleteShipment(Long shipmentId);
	
	List<ShipmentResponseDto> getByCustomer(Long customerId);
	
	List<ShipmentResponseDto> getByWarehouse(Long warehouseId);
	
	List<ShipmentResponseDto> getByDeliveryAgent(Long deliveryAgentId);
	
	List<ShipmentResponseDto> getBySourceAndDestination(String source,String destination);
	
	List<ShipmentResponseDto> getByDeliveryDate(LocalDateTime deliveryDate);
	
	Page<ShipmentResponseDto> getByPaginationAndSorting(int pageNumber, int pageSize);

}
