package com.fptu.estate.repository;

import com.fptu.estate.entities.AppointmentEntity;
import com.fptu.estate.entities.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Integer> {
  ContractEntity findByAppointment(AppointmentEntity appointment);

  @Query(value = "SELECT c.*\n"
      + "FROM contract c\n"
      + "JOIN appointment a ON c.appointment_id = a.id\n"
      + "JOIN subscription s ON a.id = s.appointment_id\n"
      + "JOIN apartment apt ON s.apartment_id = apt.id\n"
      + "WHERE apt.id = ?;",  nativeQuery = true)
  ContractEntity findByApartmentId(Integer apartId);
}
