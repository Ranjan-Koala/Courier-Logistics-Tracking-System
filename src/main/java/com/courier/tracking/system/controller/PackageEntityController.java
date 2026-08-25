package com.courier.tracking.system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courier.tracking.system.entity.PackageType;
import com.courier.tracking.system.responsedto.PackageResponseDto;
import com.courier.tracking.system.service.PackageEntityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/packages")
public class PackageEntityController {

    private final PackageEntityService packageEntityService;

    @GetMapping
    public ResponseEntity<List<PackageResponseDto>> getAllPackage() {

        List<PackageResponseDto> response =
                packageEntityService.getAllPackage();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{packageId}")
    public ResponseEntity<PackageResponseDto> getPackageById(
            @PathVariable Long packageId) {

        PackageResponseDto response =
                packageEntityService.getPackageById(packageId);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/type/{packageType}")
    public ResponseEntity<List<PackageResponseDto>> getPackageByType(
            @PathVariable PackageType packageType) {

        List<PackageResponseDto> response =
                packageEntityService.getPackageByType(packageType);

        return ResponseEntity.ok(response);
    }
}