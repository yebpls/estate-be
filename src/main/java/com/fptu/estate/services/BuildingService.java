package com.fptu.estate.services;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.DTO.BuildingDTO;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.ApartmentImageEntity;
import com.fptu.estate.entities.BuildingEntity;
import com.fptu.estate.mapper.BuildingMapper;
import com.fptu.estate.repository.BuildingRepository;
import com.fptu.estate.services.imp.BuildingServiceImp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService implements BuildingServiceImp {
  @Autowired
  private BuildingRepository buildingRepository;

  @Autowired
  private BuildingMapper buildingMapper;

  @Override
  public List<BuildingDTO> findAll() {
    List<BuildingDTO> list = buildingRepository.findAll().stream().map(buildingMapper::convertToDTO).collect(
        Collectors.toList());
    return list;
  }

  @Override
  public BuildingDTO findById(Integer id) {
    BuildingEntity building =buildingRepository.findById(id).orElse(null);
    BuildingDTO buildingDTO = buildingMapper.convertToDTO(building);
    return buildingDTO;
  }
  public List<BuildingDTO> findAllBuilding() {
    List<BuildingEntity> buildingEntities = buildingRepository.findAll();
    return buildingEntities.stream()
            .map(buildingMapper::convertToDTO)
            .collect(Collectors.toList());
  }
  public void createBuilding(BuildingDTO buildingDTO) {
    BuildingEntity buildingEntity = buildingMapper.revertToEntity(buildingDTO);
    try {
      buildingRepository.save(buildingEntity);
    } catch (Exception e) {
        throw new RuntimeException("Error create apartment" + e.getMessage());
    }
  }
  public void updateBuilding(BuildingDTO buildingDTO) {
    BuildingEntity buildingEntity = buildingMapper.revertToEntity(buildingDTO);
    try {
      buildingRepository.save(buildingEntity);
    } catch (Exception e) {
      throw new RuntimeException("Error create apartment" + e.getMessage());
    }
  }
  public boolean deleteBuilding(Integer id) {
    BuildingEntity buildingEntity = buildingRepository.findById(id).orElseThrow();
    if(buildingEntity != null) {
      buildingRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }
}
