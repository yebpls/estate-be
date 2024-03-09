package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.AppointmentDTO;
import java.util.Date;
import java.util.List;

public interface AppointmentServiceImp {
  List<AppointmentDTO> getAll();
  AppointmentDTO getAppointmentByBookingDistributionId(Integer distributionId);

  AppointmentDTO changeStatus(Integer appointmentId);

  AppointmentDTO findByApartmentId(Integer apartId);

  AppointmentDTO setMeetingDate(Integer appoindId, Date meetingDate);

  AppointmentDTO isSold(Integer appointId, Integer subId);
}
