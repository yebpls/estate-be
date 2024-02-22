package com.fptu.estate.mapper;

import com.fptu.estate.DTO.BookingDistributionDTO;
import com.fptu.estate.entities.AgencyEntity;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.BookingDistributionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingDistributionMapper {
  @Autowired
  private ModelMapper modelMapper;

  public BookingDistributionDTO convertToDTO(BookingDistributionEntity bookingDistribution){
    BookingDistributionDTO bookingDistributionDTO = modelMapper.map(bookingDistribution, BookingDistributionDTO.class);
    bookingDistributionDTO.setAgencyId(bookingDistribution.getAgency().getId());
    bookingDistributionDTO.setApartmentId(bookingDistribution.getApartment().getId());
    return bookingDistributionDTO;
  }

  public BookingDistributionEntity revertToEntity(BookingDistributionDTO bookingDistributionDTO){
    BookingDistributionEntity bookingDistribution = modelMapper.map(bookingDistributionDTO, BookingDistributionEntity.class);
    AgencyEntity agency = new AgencyEntity();
    agency.setId(bookingDistributionDTO.getAgencyId());
    bookingDistribution.setAgency(agency);
    ApartmentEntity apartment = new ApartmentEntity();
    apartment.setId(bookingDistributionDTO.getApartmentId());
    bookingDistribution.setApartment(apartment);
    return bookingDistribution;
  }
}
