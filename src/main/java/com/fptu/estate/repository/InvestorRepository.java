package com.fptu.estate.repository;

import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.entities.AppointmentEntity;
import com.fptu.estate.entities.InvestorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends JpaRepository<InvestorEntity, Integer> {
  InvestorEntity findByAccount(AccountEntity account);
  @Query(value = "SELECT\n"
      + "  investor.*\n"
      + "      FROM\n"
      + "  apartment\n"
      + "      JOIN\n"
      + "  building ON apartment.building_id = building.id\n"
      + "      JOIN\n"
      + "  project ON building.project_id = project.id\n"
      + "      JOIN\n"
      + "  investor ON project.investor_id = investor.id\n"
      + "      WHERE\n"
      + "  apartment.id =  ?;",  nativeQuery = true)
  InvestorEntity findByApartmentId(Integer apartId);

}
