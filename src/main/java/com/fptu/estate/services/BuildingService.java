package com.fptu.estate.services;

import com.fptu.estate.DTO.BuildingDTO;
import com.fptu.estate.entities.BuildingEntity;
import com.fptu.estate.mapper.BuildingMapper;
import com.fptu.estate.repository.BuildingRepository;
import com.fptu.estate.services.imp.BuildingServiceImp;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService implements BuildingServiceImp {
  @Autowired
  private BuildingRepository buildingRepository;

  @Autowired
  private BuildingMapper buildingMapper;

  @Override
  public BuildingDTO findById(Integer id) {
    BuildingEntity building =buildingRepository.findById(id).orElse(null);
    BuildingDTO buildingDTO = buildingMapper.convertToDTO(building);
    return buildingDTO;
  }
}
