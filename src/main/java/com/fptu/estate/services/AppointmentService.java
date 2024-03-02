package com.fptu.estate.services;

import com.fptu.estate.DTO.AppointmentDTO;
import com.fptu.estate.DTO.BookingDistributionDTO;
import com.fptu.estate.entities.AppointmentEntity;
import com.fptu.estate.entities.BookingDistributionEntity;
import com.fptu.estate.mapper.AppointmentMapper;
import com.fptu.estate.mapper.BookingDistributionMapper;
import com.fptu.estate.repository.AppointmentRepository;
import com.fptu.estate.repository.BookingDistributionRepository;
import com.fptu.estate.services.imp.AppointmentServiceImp;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService implements AppointmentServiceImp {
  @Autowired
  private AppointmentMapper appointmentMapper;
  @Autowired
  private BookingDistributionMapper bookingDistributionMapper;

  private final AppointmentRepository appointmentRepository;
  private final BookingDistributionRepository bookingDistributionRepository;

  public AppointmentService(AppointmentRepository appointmentRepository,
      BookingDistributionRepository bookingDistributionRepository) {
    this.appointmentRepository = appointmentRepository;
    this.bookingDistributionRepository = bookingDistributionRepository;
  }

  @Override
  public List<AppointmentDTO> getAll() {

    return appointmentRepository.findAll().stream().map(appointmentMapper::convertToDTO).collect(
        Collectors.toList());
  }

  @Override
  public AppointmentDTO getAppointmentByBookingDistributionId(Integer distributionId) {
    BookingDistributionEntity bookingDistribution  = bookingDistributionRepository.findById(distributionId).orElseThrow(null);
    AppointmentDTO appointmentDTO = appointmentMapper.convertToDTO(appointmentRepository.findByDistribution(bookingDistribution));
    return appointmentDTO;
  }

  @Override
  public AppointmentDTO changeStatus(Integer appointmentId) {
    AppointmentEntity appointment = appointmentRepository.findById(appointmentId).orElseThrow(null);
    Integer status = appointment.getAppointmentStatus();
    if(status == 0){
      appointment.setAppointmentStatus(1);
    }
    if(status == 1){
      appointment.setAppointmentStatus(0);
    }
    appointmentRepository.save(appointment);
    return appointmentMapper.convertToDTO(appointment);
  }
}
