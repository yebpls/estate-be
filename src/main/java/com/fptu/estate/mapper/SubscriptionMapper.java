package com.fptu.estate.mapper;

import com.fptu.estate.DTO.AppointmentDTO;
import com.fptu.estate.DTO.SubscriptionDTO;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.AppointmentEntity;
import com.fptu.estate.entities.CustomerEntity;
import com.fptu.estate.entities.SubscriptionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {
  @Autowired
  private ModelMapper modelMapper;

  public SubscriptionDTO convertToDTO(SubscriptionEntity subscription){
    SubscriptionDTO subscriptionDTO = modelMapper.map(subscription, SubscriptionDTO.class);
    subscriptionDTO.setApartmentId(subscription.getApartment().getId());
    subscriptionDTO.setCustomerId(subscription.getCustomer().getId());
    subscriptionDTO.setAppointmentId(subscription.getAppointment().getId());
    return subscriptionDTO;
  }

  public SubscriptionEntity revertToEntity(SubscriptionDTO subscriptionDTO){
    SubscriptionEntity subscription = modelMapper.map(subscriptionDTO, SubscriptionEntity.class);
    ApartmentEntity apartment = new ApartmentEntity();
    apartment.setId(subscriptionDTO.getApartmentId());
    AppointmentEntity appointment = new AppointmentEntity();
    appointment.setId(subscriptionDTO.getAppointmentId());
    CustomerEntity customer = new CustomerEntity();
    customer.setId(subscriptionDTO.getCustomerId());
    subscription.setApartment(apartment);
    subscription.setCustomer(customer);
    subscription.setAppointment(appointment);
    return subscription;
  }
}
