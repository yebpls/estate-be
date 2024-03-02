package com.fptu.estate.services;

import com.fptu.estate.DTO.AppointmentDTO;
import com.fptu.estate.DTO.BookingDistributionDTO;
import com.fptu.estate.entities.AgencyEntity;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.BookingDistributionEntity;
import com.fptu.estate.mapper.AppointmentMapper;
import com.fptu.estate.mapper.BookingDistributionMapper;
import com.fptu.estate.repository.AgencyRepository;
import com.fptu.estate.repository.ApartmentRepository;
import com.fptu.estate.repository.AppointmentRepository;
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
  @Autowired
  private ApartmentRepository apartmentRepository;
  @Autowired
  private AppointmentRepository appointmentRepository;
  @Autowired
  private AppointmentMapper appointmentMapper;

  @Override
  public List<BookingDistributionDTO> getAllByAgencyId(Integer id) {
    AgencyEntity agency = agencyRepository.findById(id).orElseThrow(null);
    return bookingDistributionRepository.findAllByAgency(agency).stream().map(bookingDistributionMapper::convertToDTO).collect(
        Collectors.toList());
  }

  @Override
  public List<BookingDistributionDTO> getAllByStatus(Integer status) {
    return bookingDistributionRepository.findAllByBookingStatus(status).stream().map(bookingDistributionMapper::convertToDTO).collect(
        Collectors.toList());

  }

  @Override
  public void createBookingDistribution(BookingDistributionDTO bookingDistributionDTO) {
    BookingDistributionEntity bookingDistribution = bookingDistributionMapper.revertToEntity(bookingDistributionDTO);
    ApartmentEntity apartment = apartmentRepository.findById(bookingDistributionDTO.getApartmentId()).orElseThrow(null) ;
    Integer status = apartment.getStatus();
    if(status != 1){
      throw new RuntimeException("Dự án đã có người tiếp nhận bán hoặc đã bán !!!");
    }
    try {
      apartment.setStatus(2);
      apartmentRepository.save(apartment);
      bookingDistributionRepository.save(bookingDistribution);
      AppointmentDTO appointmentDTO = new AppointmentDTO();
      appointmentDTO.setDistributionId(bookingDistribution.getId());
      appointmentDTO.setUpdateDate(bookingDistribution.getCreateDate());
      appointmentDTO.setMeetingDate(bookingDistribution.getCreateDate());
      appointmentDTO.setAppointmentStatus(0);
      appointmentRepository.save(appointmentMapper.revertToEntity(appointmentDTO));

    } catch (Exception e) {
      throw new RuntimeException("Error create booking distribution " + e.getMessage());
    }
  }
}
