package com.fptu.estate.services;

import com.fptu.estate.DTO.BuildingDTO;
import com.fptu.estate.entities.BuildingEntity;
import com.fptu.estate.entities.ProjectEntity;
import com.fptu.estate.mapper.BuildingMapper;
import com.fptu.estate.repository.BuildingRepository;
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

  @Override
  public List<BuildingDTO> findAllByProjectId(Integer id) {
    ProjectEntity project = projectRepository.findById(id).orElseThrow(null);
    List<BuildingDTO> list = buildingRepository.findAllByProject(project).stream().map(buildingMapper::convertToDTO).collect(
        Collectors.toList());
    return list;
  }

  @Override
  public void createBuilding(BuildingDTO buildingDTO) {
    BuildingEntity building = buildingMapper.revertToEntity(buildingDTO);
    try {
      buildingRepository.save(building);
    } catch (Exception e) {
      throw new RuntimeException("Error create building " + e.getMessage());
    }
  }
}
