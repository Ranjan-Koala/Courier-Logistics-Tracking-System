package com.courier.tracking.system.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courier.tracking.system.entity.PackageEntity;
import com.courier.tracking.system.entity.PackageType;

@Repository
public interface PackageEntityRepository extends JpaRepository<PackageEntity, Long>{
	
	List<PackageEntity> findByPackageType(PackageType packageType);

}
