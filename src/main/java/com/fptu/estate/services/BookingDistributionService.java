package com.fptu.estate.services;

import com.fptu.estate.DTO.BookingDistributionDTO;
import com.fptu.estate.entities.AgencyEntity;
import com.fptu.estate.entities.BookingDistributionEntity;
import com.fptu.estate.mapper.BookingDistributionMapper;
import com.fptu.estate.repository.AgencyRepository;
import com.fptu.estate.repository.BookingDistributionRepository;
import com.fptu.estate.services.imp.BookingDistributionServiceImp;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingDistributionService implements BookingDistributionServiceImp {
  @Autowired
  private AgencyRepository agencyRepository;

  @Autowired
  private BookingDistributionRepository bookingDistributionRepository;

  @Autowired
  private BookingDistributionMapper bookingDistributionMapper;

  @Override
  public List<BookingDistributionDTO> getAllByAgencyId(Integer id) {
    AgencyEntity agency = agencyRepository.findById(id).orElseThrow(null);
    return bookingDistributionRepository.findAllByAgency(agency).stream().map(bookingDistributionMapper::convertToDTO).collect(
        Collectors.toList());
  }
}
