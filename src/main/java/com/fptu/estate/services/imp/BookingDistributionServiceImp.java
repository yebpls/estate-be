package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.BookingDistributionDTO;
import com.fptu.estate.entities.BookingDistributionEntity;
import java.util.List;

public interface BookingDistributionServiceImp {
  List<BookingDistributionDTO> getAllByAgencyId(Integer id);

  List<BookingDistributionDTO> getAllByStatus(Integer status);

  boolean cancelBookingDistribution(Integer distributionId);
  BookingDistributionDTO createBookingDistribution(BookingDistributionDTO bookingDistributionDTO);
}
