package com.fptu.estate.repository;

import com.fptu.estate.entities.AppointmentEntity;
import com.fptu.estate.entities.BookingDistributionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {
  AppointmentEntity findByDistribution(BookingDistributionEntity distribution);

  @Query(value = "SELECT appt.*\n"
      + "FROM appointment AS appt\n"
      + "JOIN bookingdistribution AS bd ON appt.distribution_id = bd.id\n"
      + "WHERE bd.apartment_id = ?;",  nativeQuery = true)
  AppointmentEntity findByApartmentId(Integer apartId);
}
