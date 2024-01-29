package com.fptu.estate.mapper;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.ApartmentImageEntity;
import com.fptu.estate.entities.BuildingEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApartmentMapper {
  @Autowired
  private ModelMapper modelMapper;

  public ApartmentDTO convertToDTO(ApartmentEntity apartment){
    ApartmentDTO apartmentDTO = modelMapper.map(apartment, ApartmentDTO.class);
    apartmentDTO.setBuildingId(apartment.getBuilding().getId());
    apartmentDTO.setProjectName(apartment.getBuilding().getProject().getName());
    apartmentDTO.setCityName(apartment.getBuilding().getCity().getCityName());
    return apartmentDTO;
  }

  public ApartmentEntity revertToEntity(ApartmentDTO apartmentDTO){
    ApartmentEntity apartment = modelMapper.map(apartmentDTO, ApartmentEntity.class);
    BuildingEntity building = new BuildingEntity();
    building.setId(apartmentDTO.getBuildingId());
    apartment.setBuilding(building);
    return apartment;
  }

  private List<String> mapImages(List<ApartmentImageEntity> apartmentImages) {
    return apartmentImages.stream()
        .map(ApartmentImageEntity::getImageUrl)
        .collect(Collectors.toList());
  }

}