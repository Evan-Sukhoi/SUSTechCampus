package com.sustech.campus.service.abandoned;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sustech.campus.repository.BuildingImageRepository;

@Service
public class BuildingImageService {

    private final BuildingImageRepository buildingImageRepository;

    @Autowired
    public BuildingImageService(BuildingImageRepository buildingImageRepository) {
        this.buildingImageRepository = buildingImageRepository;
    }


}
