package com.fptu.estate.repository;

import com.fptu.estate.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
  AccountEntity findByEmail(String email);

  AccountEntity findByIdAndStatus(Integer id, Integer status);



}
