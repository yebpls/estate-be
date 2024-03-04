package com.fptu.estate.services;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.DTO.BuildingDTO;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.ApartmentImageEntity;
import com.fptu.estate.entities.BuildingEntity;
import com.fptu.estate.entities.CityEntity;
import com.fptu.estate.entities.ProjectEntity;
import com.fptu.estate.mapper.BuildingMapper;
import com.fptu.estate.repository.BuildingRepository;
import com.fptu.estate.repository.CityRepository;
import com.fptu.estate.repository.ProjectRepository;
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
  @Autowired
  private ProjectRepository projectRepository;
  @Autowired
  private CityRepository cityRepository;

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
  public BuildingDTO createBuilding(BuildingDTO buildingDTO) {
    BuildingEntity buildingEntity = buildingMapper.revertToEntity(buildingDTO);
    try {
      buildingRepository.save(buildingEntity);
      BuildingDTO buildingDTO1 = buildingMapper.convertToDTO(buildingEntity);
      return buildingDTO1;
    } catch (Exception e) {
        throw new RuntimeException("Error create apartment" + e.getMessage());
    }
  }
  public BuildingDTO updateBuilding(Integer id, BuildingDTO buildingDTO) {
    BuildingEntity buildingEntity = buildingRepository.findById(id).orElseThrow(null);
    try {
      buildingEntity.setBuildingName(buildingDTO.getBuildingName());
      buildingEntity.setAddress(buildingDTO.getAddress());
      CityEntity city = cityRepository.findById(buildingDTO.getCityId()).orElseThrow(null);
//      city.setId(buildingDTO.getCityId());
      buildingEntity.setCity(city);
      buildingRepository.save(buildingEntity);
      BuildingDTO buildingDTO1 = buildingMapper.convertToDTO(buildingEntity);
      return buildingDTO1;
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

  @Override
  public List<BuildingDTO> findAllByProjectId(Integer id) {
    ProjectEntity project = projectRepository.findById(id).orElseThrow();
    List<BuildingDTO> list = buildingRepository.findAllByProject(project).stream().map(buildingMapper::convertToDTO).collect(
        Collectors.toList());
    return list;
  }
}
