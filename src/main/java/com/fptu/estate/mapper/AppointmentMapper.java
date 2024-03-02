package com.fptu.estate.mapper;

import com.fptu.estate.DTO.ApartmentDTO;
import com.fptu.estate.DTO.AppointmentDTO;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.AppointmentEntity;
import com.fptu.estate.entities.BookingDistributionEntity;
import com.fptu.estate.entities.BuildingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
  @Autowired
  private ModelMapper modelMapper;

  public AppointmentDTO convertToDTO(AppointmentEntity appointment){
    AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
    appointmentDTO.setDistributionId(appointment.getDistribution().getId());
    return appointmentDTO;
  }

  public AppointmentEntity revertToEntity(AppointmentDTO appointmentDTO){
    AppointmentEntity appointment = modelMapper.map(appointmentDTO, AppointmentEntity.class);
    BookingDistributionEntity bookingDistribution = new BookingDistributionEntity();
    bookingDistribution.setId(appointment.getDistribution().getId());
    appointment.setDistribution(bookingDistribution);
    return appointment;
  }
}
