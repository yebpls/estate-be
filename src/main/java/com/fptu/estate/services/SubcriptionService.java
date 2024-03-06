package com.fptu.estate.services;

import com.fptu.estate.DTO.CustomerDTO;
import com.fptu.estate.DTO.SubscriptionDTO;
import com.fptu.estate.entities.AppointmentEntity;
import com.fptu.estate.entities.CustomerEntity;
import com.fptu.estate.entities.SubscriptionEntity;
import com.fptu.estate.mapper.SubscriptionMapper;
import com.fptu.estate.repository.AppointmentRepository;
import com.fptu.estate.repository.CustomerRepository;
import com.fptu.estate.repository.SubscriptionRepository;
import com.fptu.estate.services.imp.SubcriptionServiceImp;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubcriptionService implements SubcriptionServiceImp {

  @Autowired
  private SubscriptionMapper subscriptionMapper;
  @Autowired
  private SubscriptionRepository subscriptionRepository;
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private AppointmentRepository appointmentRepository;

  @Override
  public List<SubscriptionDTO> getAll() {
    return subscriptionRepository.findAll().stream().map(subscriptionMapper::convertToDTO).collect(
        Collectors.toList());
  }

  @Override
  public SubscriptionDTO findById(Integer id) {
    return subscriptionMapper.convertToDTO(subscriptionRepository.findById(id).orElseThrow(null));
  }

  @Override
  public List<SubscriptionDTO> findAllByCustomerId(Integer cusId) {
    CustomerEntity customer = customerRepository.findById(cusId).orElseThrow(null);
    List<SubscriptionDTO> list = subscriptionRepository.findAllByCustomer(customer).stream().map(subscriptionMapper::convertToDTO).collect(
        Collectors.toList());
    return list;
  }

  @Override
  public SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO) {
    SubscriptionEntity subscription = subscriptionMapper.revertToEntity(subscriptionDTO);
    try {
      subscriptionRepository.save(subscription);
      SubscriptionDTO subscriptionDTO1 = subscriptionMapper.convertToDTO(subscription);
      return subscriptionDTO1;
    } catch (Exception e) {
      throw new RuntimeException("Error create subscription " + e.getMessage());

    }
  }

  @Override
  public SubscriptionDTO changeStatus(Integer subscriptionId, Integer status) {
    SubscriptionEntity subscription = subscriptionRepository.findById(subscriptionId).orElseThrow(null);
    subscription.setSubscriptionStatus(status);
    try{
      AppointmentEntity appointment = appointmentRepository.findById(subscription.getAppointment().getId()).orElseThrow(null);
      subscriptionRepository.save(subscription);
      if(subscription.getSubscriptionStatus() == 2){
        appointment.setAppointmentStatus(1);
        appointmentRepository.save(appointment);
      }
      if(subscription.getSubscriptionStatus() == 1){
        appointment.setAppointmentStatus(0);
        appointmentRepository.save(appointment);
      }
      SubscriptionDTO subscriptionDTO = subscriptionMapper.convertToDTO(subscription) ;
      return subscriptionDTO;
    } catch (Exception e){
      System.out.println("Error at change status subscription: " + e.getMessage());
      return null;
    }
  }
}
