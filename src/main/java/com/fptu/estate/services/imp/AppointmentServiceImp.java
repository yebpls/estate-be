package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.AppointmentDTO;
import java.util.List;

public interface AppointmentServiceImp {
  List<AppointmentDTO> getAll();
  AppointmentDTO getAppointmentByBookingDistributionId(Integer distributionId);

  AppointmentDTO changeStatus(Integer appointmentId);
}
