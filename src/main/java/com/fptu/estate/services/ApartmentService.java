package com.fptu.estate.services;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.ApartmentImageEntity;
import com.fptu.estate.mapper.ApartmentMapper;
import com.fptu.estate.repository.ApartmentImageRepository;
import com.fptu.estate.repository.ApartmentRepository;
import com.fptu.estate.services.imp.ApartmentServiceImp;
import java.util.List;
import java.util.stream.Collectors;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApartmentService implements ApartmentServiceImp {

  @Autowired
  private ApartmentRepository apartmentRepository;

  @Autowired
  private ApartmentMapper apartmentMapper;

  @Autowired
  private ApartmentImageRepository apartmentImageRepository;

  @Override
  public List<ApartmentDTO> findAll() {
    List<ApartmentEntity> apartments = apartmentRepository.findAllByStatus(1);
    return apartments.stream()
        .map(apartment -> {
          List<ApartmentImageEntity> apartmentImages = apartmentImageRepository.findAllByApartment(apartment);
          ApartmentDTO apartmentDTO = apartmentMapper.convertToDTO(apartment);
          if(apartmentImages.size() > 0){
            apartmentDTO.setMainImage(apartmentImages.get(0).getImageUrl());
          } else {
            apartmentDTO.setMainImage("");

          }
          return apartmentDTO;
        })
        .collect(Collectors.toList());
//    return apartmentRepository.findAllByStatus(1)
//        .stream().map(apartmentMapper::convertToDTO).collect(Collectors.toList());
  }

  @Override
  public ApartmentDTO findById(Integer id) {
    ApartmentDTO apartment = apartmentMapper.convertToDTO(apartmentRepository.findByIdAndStatus(id, 1)) ;
    return apartment;
  }

    @Override
    public ApartmentDTO createApartment(ApartmentDTO newApartment) {
        try {
            // Perform any necessary validation or business logic before saving the apartment
            ApartmentEntity apartmentEntity = apartmentMapper.revertToEntity(newApartment);

            apartmentEntity.setStatus(1);

            // Save the apartment entity and get the saved entity
            apartmentRepository.save(apartmentEntity);

            // Convert the saved entity back to DTO and return it
            return apartmentMapper.convertToDTO(apartmentEntity);
        } catch (Exception e) {
            // Handle exceptions, log errors, or throw custom exceptions as needed
            throw new RuntimeException("Failed to create apartment", e);
        }
    }
    public ApartmentDTO updateApartment(ApartmentDTO updatedApartment) throws NotFoundException {
        try {
            // Check if the apartment exists
            ApartmentEntity existingApartment = apartmentRepository.findByIdAndStatus(updatedApartment.getId(), 1);
            if (existingApartment == null) {
                throw new NotFoundException("Apartment not found");
            }

            // Update apartment details
            // Note: You may want to validate and perform additional business logic here

            // Map updatedApartment DTO to an entity
            ApartmentEntity updatedEntity = apartmentMapper.revertToEntity(updatedApartment);

            // Set the status of the updated entity (assuming 1 means active)
            updatedEntity.setStatus(1);

            // Save the updated entity
            ApartmentEntity savedEntity = apartmentRepository.save(updatedEntity);

            // Convert the saved entity back to DTO and return it
            return apartmentMapper.convertToDTO(savedEntity);
        } catch (NotFoundException e) {
            throw e; // Re-throw NotFoundException
        } catch (Exception e) {
            throw new RuntimeException("Failed to update apartment", e);
        }
    }
    public void deleteApartment(Integer id) throws NotFoundException {
        try {
            // Check if the apartment exists
            ApartmentEntity existingApartment = apartmentRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Apartment not found"));

            // Perform any additional checks or business logic if needed

            // Delete the apartment
            apartmentRepository.delete(existingApartment);
        } catch (NotFoundException e) {
            throw e; // Re-throw NotFoundException
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete apartment", e);
        }
    }
}
