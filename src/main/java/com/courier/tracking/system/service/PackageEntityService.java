package com.courier.tracking.system.service;

import java.util.List;

import com.courier.tracking.system.entity.PackageType;
import com.courier.tracking.system.responsedto.PackageResponseDto;

public interface PackageEntityService {
	
	List<PackageResponseDto> getAllPackage();
	
	PackageResponseDto getPackageById(Long packageId);
	
	List<PackageResponseDto> getPackageByType(PackageType packageType);

}
