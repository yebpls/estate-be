package com.fptu.estate.DTO;


import java.util.Date;
import lombok.Data;




@Data
public class AppointmentDTO {
  private Integer id;

  private Date meetingDate;

  private Date updateDate;

  private Integer appointmentStatus;

  private Integer distributionId;

}
