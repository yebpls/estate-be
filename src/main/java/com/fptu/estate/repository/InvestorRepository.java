package com.fptu.estate.repository;

import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.entities.InvestorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends JpaRepository<InvestorEntity, Integer> {
  InvestorEntity findByAccount(AccountEntity account);

}
