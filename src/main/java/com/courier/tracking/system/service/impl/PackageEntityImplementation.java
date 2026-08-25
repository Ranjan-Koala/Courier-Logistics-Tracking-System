package com.courier.tracking.system.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.courier.tracking.system.entity.PackageEntity;
import com.courier.tracking.system.entity.PackageType;
import com.courier.tracking.system.repository.PackageEntityRepository;
import com.courier.tracking.system.responsedto.PackageResponseDto;
import com.courier.tracking.system.service.PackageEntityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PackageEntityImplementation implements PackageEntityService {

	private final PackageEntityRepository packageEntityRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<PackageResponseDto> getAllPackage() {
		List<PackageEntity> packageEntities = packageEntityRepository.findAll();
		return packageEntities.stream().map(item -> modelMapper.map(item, PackageResponseDto.class)).toList();

	}

	@Override
	public PackageResponseDto getPackageById(Long packageId) {
		PackageEntity packageEntity = packageEntityRepository.findById(packageId)
				.orElseThrow(() -> new IllegalArgumentException("Package is not found with Id" + packageId));
		return modelMapper.map(packageEntity, PackageResponseDto.class);
	}

	@Override
	public List<PackageResponseDto> getPackageByType(PackageType packageType) {

		List<PackageEntity> packages = packageEntityRepository.findByPackageType(packageType);

		if (packages.isEmpty()) {
			throw new IllegalArgumentException("No packages found with type: " + packageType);
		}

		return packages.stream().map(packageEntity -> modelMapper.map(packageEntity, PackageResponseDto.class))
				.toList();
	}

}
