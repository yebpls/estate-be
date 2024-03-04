package com.fptu.estate.services.imp;

import com.fptu.estate.DTO.SubscriptionDTO;
import java.util.List;

public interface SubcriptionServiceImp {
  List<SubscriptionDTO> getAll();

  SubscriptionDTO findById(Integer id);

  List<SubscriptionDTO> findAllByCustomerId(Integer cusId);
  
  SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO);

  SubscriptionDTO changeStatus(Integer subscriptionId, Integer status);

}
