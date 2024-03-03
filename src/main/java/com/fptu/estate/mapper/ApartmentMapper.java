package com.fptu.estate.mapper;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.ApartmentImageEntity;
import com.fptu.estate.entities.BuildingEntity;
import com.fptu.estate.entities.CityEntity;
import com.fptu.estate.entities.ProjectEntity;
import com.fptu.estate.repository.BuildingRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApartmentMapper {

  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private BuildingRepository buildingRepository;

  public ApartmentDTO convertToDTO(ApartmentEntity apartment) {
    ApartmentDTO apartmentDTO = modelMapper.map(apartment, ApartmentDTO.class);

    BuildingEntity building = apartment.getBuilding();

    apartmentDTO.setBuildingId(building.getId());
    ProjectEntity project = building.getProject();
    apartmentDTO.setProjectName(project.getName());

    CityEntity city = building.getCity();
    apartmentDTO.setCityName(city.getCityName());

    return apartmentDTO;
  }

  public ApartmentEntity revertToEntity(ApartmentDTO apartmentDTO) {
    ApartmentEntity apartment = modelMapper.map(apartmentDTO, ApartmentEntity.class);
    BuildingEntity building = buildingRepository.findById(apartmentDTO.getBuildingId())
        .orElseThrow(() -> new RuntimeException(
            "Building not found with id: " + apartmentDTO.getBuildingId()));
    apartment.setBuilding(building);
    return apartment;
  }

  private List<String> mapImages(List<ApartmentImageEntity> apartmentImages) {
    return apartmentImages.stream()
        .map(ApartmentImageEntity::getImageUrl)
        .collect(Collectors.toList());
  }

}
