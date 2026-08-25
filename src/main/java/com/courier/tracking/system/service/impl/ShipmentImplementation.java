package com.courier.tracking.system.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.courier.tracking.system.entity.DeliveryAgent;
import com.courier.tracking.system.entity.PackageEntity;
import com.courier.tracking.system.entity.Payment;
import com.courier.tracking.system.entity.Shipment;
import com.courier.tracking.system.entity.ShipmentStatus;
import com.courier.tracking.system.entity.TrackingHistory;
import com.courier.tracking.system.entity.Warehouse;
import com.courier.tracking.system.repository.CustomerRepository;
import com.courier.tracking.system.repository.DeliveryAgentRepository;
import com.courier.tracking.system.repository.ShipmentRepository;
import com.courier.tracking.system.repository.WarehouseRepository;
import com.courier.tracking.system.requestdto.PackageRequestDto;
import com.courier.tracking.system.requestdto.PaymentRequestDto;
import com.courier.tracking.system.requestdto.ShipmentRequestDto;
import com.courier.tracking.system.requestdto.TrackingHistoryRequestDto;
import com.courier.tracking.system.responsedto.ShipmentResponseDto;
import com.courier.tracking.system.service.ShipmentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentImplementation implements ShipmentService {

	private final ShipmentRepository shipmentRepository;
	private final CustomerRepository customerRepository;
	private final WarehouseRepository warehouseRepository;
	private final DeliveryAgentRepository deliveryAgentRepository;
	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public ShipmentResponseDto createShipment(ShipmentRequestDto shipmentRequestDto,
			PackageRequestDto packageRequestDto, PaymentRequestDto paymentRequestDto,
			TrackingHistoryRequestDto trackingHistoryRequestDto) {

		Shipment shipment = modelMapper.map(shipmentRequestDto, Shipment.class);

		PackageEntity item = modelMapper.map(packageRequestDto, PackageEntity.class);

		Payment payment = modelMapper.map(paymentRequestDto, Payment.class);

		TrackingHistory trackingHistory = modelMapper.map(trackingHistoryRequestDto, TrackingHistory.class);

		shipment.setPackageEntity(item);
		shipment.setPayment(payment);
		shipment.getTrackingHistory().add(trackingHistory);

		item.setShipment(shipment);
		payment.setShipment(shipment);
		trackingHistory.setShipment(shipment);

		Shipment savedshipment = shipmentRepository.save(shipment);

		return modelMapper.map(savedshipment, ShipmentResponseDto.class);
	}

	@Override
	public List<ShipmentResponseDto> getAllShipment() {
		List<Shipment> shipments = shipmentRepository.findAll();

		if (shipments.isEmpty()) {
			throw new IllegalArgumentException("No shipments found");
		}

		return shipments.stream().map(shipment -> modelMapper.map(shipment, ShipmentResponseDto.class)).toList();
	}

	@Override
	public ShipmentResponseDto getById(Long shipmentId) {
		Shipment shipment = shipmentRepository.findById(shipmentId)
				.orElseThrow(() -> new IllegalArgumentException("Shipment not found with Id " + shipmentId));

		return modelMapper.map(shipment, ShipmentResponseDto.class);
	}

	@Override
	public ShipmentResponseDto getByTrackingNumber(String trackingNumber) {
		Shipment shipment = shipmentRepository.findByTrackingNumber(trackingNumber).orElseThrow(
				() -> new IllegalArgumentException("Shipment not found with tracking number " + trackingNumber));

		return modelMapper.map(shipment, ShipmentResponseDto.class);
	}

	@Override
	public ShipmentResponseDto updateShipmentStatus(Long shipmentId, Map<String, Object> updates) {
		Shipment shipment = shipmentRepository.findById(shipmentId)
				.orElseThrow(() -> new IllegalArgumentException("Shipment not found with Id " + shipmentId));

		updates.forEach((field, value) -> {

			switch (field) {

			case "shipmentStatus" -> {

				ShipmentStatus status = ShipmentStatus.valueOf(value.toString().toUpperCase());

				shipment.setShipmentStatus(status);
			}

			default -> throw new IllegalArgumentException("Invalid field: " + field);
			}
		});

		Shipment updatedShipment = shipmentRepository.save(shipment);

		return modelMapper.map(updatedShipment, ShipmentResponseDto.class);
	}

	@Override
	public ShipmentResponseDto assignDeliveryAgent(Long shipmentId, Long agentId) {
		Shipment shipment = shipmentRepository.findById(shipmentId)
				.orElseThrow(() -> new IllegalArgumentException("Shipment not found with Id " + shipmentId));

		DeliveryAgent deliveryAgent = deliveryAgentRepository.findById(agentId)
				.orElseThrow(() -> new IllegalArgumentException("Delivery agent not found with Id " + agentId));

		// Business validation
		if (!deliveryAgent.getAvailabilityStatus()) {
			throw new IllegalArgumentException("Delivery agent is currently unavailable");
		}

		shipment.setDeliveryAgent(deliveryAgent);

		Shipment updatedShipment = shipmentRepository.save(shipment);

		return modelMapper.map(updatedShipment, ShipmentResponseDto.class);
	}

	@Override
	public ShipmentResponseDto assignWarehouse(Long shipmentId, Long warehouseId) {
		Shipment shipment = shipmentRepository.findById(shipmentId)
				.orElseThrow(() -> new IllegalArgumentException("Shipment not found with Id " + shipmentId));

		Warehouse warehouse = warehouseRepository.findById(warehouseId)
				.orElseThrow(() -> new IllegalArgumentException("Warehouse not found with Id " + warehouseId));

		shipment.setWarehouse(warehouse);

		Shipment updatedShipment = shipmentRepository.save(shipment);

		return modelMapper.map(updatedShipment, ShipmentResponseDto.class);
	}

	@Override
	@Transactional
	public void deleteShipment(Long shipmentId) {
		Shipment shipment = shipmentRepository.findById(shipmentId)
				.orElseThrow(() -> new IllegalArgumentException("Shipment not found with Id " + shipmentId));

		shipmentRepository.delete(shipment);

	}

	@Override
	public List<ShipmentResponseDto> getByCustomer(Long customerId) {
		List<Shipment> shipments = shipmentRepository.findByCustomerCustomerId(customerId);

		if (shipments.isEmpty()) {
			throw new IllegalArgumentException("No shipments found for customer Id " + customerId);
		}

		return shipments.stream().map(shipment -> modelMapper.map(shipment, ShipmentResponseDto.class)).toList();
	}

	@Override
	public List<ShipmentResponseDto> getByWarehouse(Long warehouseId) {
		List<Shipment> shipments = shipmentRepository.findByWarehouseWarehouseId(warehouseId);

		if (shipments.isEmpty()) {
			throw new IllegalArgumentException("No shipments found for warehouse Id " + warehouseId);
		}

		return shipments.stream().map(shipment -> modelMapper.map(shipment, ShipmentResponseDto.class)).toList();
	}

	@Override
	public List<ShipmentResponseDto> getByDeliveryAgent(Long deliveryAgentId) {
		List<Shipment> shipments = shipmentRepository.findByDeliveryAgentDeliveryAgentId(deliveryAgentId);

		if (shipments.isEmpty()) {
			throw new IllegalArgumentException("No shipments found for delivery agent Id " + deliveryAgentId);
		}

		return shipments.stream().map(shipment -> modelMapper.map(shipment, ShipmentResponseDto.class)).toList();
	}

	@Override
	public List<ShipmentResponseDto> getBySourceAndDestination(String source, String destination) {
		List<Shipment> shipments = shipmentRepository.findBySourceAndDestination(source, destination);

		if (shipments.isEmpty()) {
			throw new IllegalArgumentException("No shipments found from " + source + " to " + destination);
		}

		return shipments.stream().map(shipment -> modelMapper.map(shipment, ShipmentResponseDto.class)).toList();
	}

	@Override
	public List<ShipmentResponseDto> getByDeliveryDate(LocalDateTime deliveryDate) {
		List<Shipment> shipments = shipmentRepository.findByDeliveryDate(deliveryDate);

		if (shipments.isEmpty()) {
			throw new IllegalArgumentException("No shipments found with delivery date " + deliveryDate);
		}

		return shipments.stream().map(shipment -> modelMapper.map(shipment, ShipmentResponseDto.class)).toList();
	}

	@Override
	public Page<ShipmentResponseDto> getByPaginationAndSorting(int pageNumber, int pageSize) {
		if (pageNumber < 0) {
			throw new IllegalArgumentException("Page number cannot be negative");
		}

		if (pageSize <= 0) {
			throw new IllegalArgumentException("Page size must be greater than 0");
		}

		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,
				Sort.by(Sort.Direction.DESC, "shipmentDateTime"));

		Page<Shipment> shipmentPage = shipmentRepository.findAll(pageRequest);

		return shipmentPage.map(shipment -> modelMapper.map(shipment, ShipmentResponseDto.class));
	}

}
