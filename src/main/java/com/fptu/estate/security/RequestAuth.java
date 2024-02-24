package com.fptu.estate.security;

import com.fptu.estate.DTO.AccountDTO;
import com.fptu.estate.entities.AccountEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class RequestAuth {
    public static Optional<AccountEntity> getAccountDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null && authentication.getPrincipal() instanceof AccountEntity) {
            return Optional.of((AccountEntity) authentication.getPrincipal());
        }
        return Optional.empty();
    }
}
