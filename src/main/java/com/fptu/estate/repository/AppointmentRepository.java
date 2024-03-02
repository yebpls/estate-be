package com.fptu.estate.repository;

import com.fptu.estate.entities.AppointmentEntity;
import com.fptu.estate.entities.BookingDistributionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {
  AppointmentEntity findByDistribution(BookingDistributionEntity distribution);
}
