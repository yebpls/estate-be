package com.fptu.estate.mapper;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.DTO.BuildingDTO;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.BuildingEntity;
import com.fptu.estate.entities.CityEntity;
import com.fptu.estate.entities.ProjectEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingMapper {
  @Autowired
  private ModelMapper modelMapper;

  public BuildingDTO convertToDTO(BuildingEntity building){
    BuildingDTO buildingDTO = modelMapper.map(building, BuildingDTO.class);
    buildingDTO.setCityId(building.getCity().getId());
    buildingDTO.setProjectId(building.getProject().getId());
    return buildingDTO;
  }

  public BuildingEntity revertToEntity(BuildingDTO buildingDTO){
    BuildingEntity building = modelMapper.map(buildingDTO, BuildingEntity.class);
    CityEntity city = new CityEntity();
    city.setId(buildingDTO.getCityId());
    building.setCity(city);
    ProjectEntity project = new ProjectEntity();
    project.setId(buildingDTO.getProjectId());
    building.setProject(project);
    return building;
  }
}
