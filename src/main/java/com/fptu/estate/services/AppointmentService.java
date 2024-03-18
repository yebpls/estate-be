package com.fptu.estate.services;

import com.fptu.estate.DTO.AppointmentDTO;
import com.fptu.estate.DTO.BookingDistributionDTO;
import com.fptu.estate.DTO.ContractDTO;
import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.entities.AgencyEntity;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.AppointmentEntity;
import com.fptu.estate.entities.BookingDistributionEntity;
import com.fptu.estate.entities.BuildingEntity;
import com.fptu.estate.entities.CustomerEntity;
import com.fptu.estate.entities.InvestorEntity;
import com.fptu.estate.entities.SubscriptionEntity;
import com.fptu.estate.mapper.AppointmentMapper;
import com.fptu.estate.mapper.BookingDistributionMapper;
import com.fptu.estate.repository.AccountRepository;
import com.fptu.estate.repository.AgencyRepository;
import com.fptu.estate.repository.ApartmentRepository;
import com.fptu.estate.repository.AppointmentRepository;
import com.fptu.estate.repository.BookingDistributionRepository;
import com.fptu.estate.repository.BuildingRepository;
import com.fptu.estate.repository.ContractRepository;
import com.fptu.estate.repository.CustomerRepository;
import com.fptu.estate.repository.InvestorRepository;
import com.fptu.estate.repository.SubscriptionRepository;
import com.fptu.estate.services.imp.AppointmentServiceImp;
import com.fptu.estate.services.imp.ContractServiceImp;
import com.fptu.estate.services.imp.TransactionServiceImp;
import jakarta.persistence.EntityNotFoundException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService implements AppointmentServiceImp {
  @Autowired
  private AppointmentMapper appointmentMapper;
  @Autowired
  private BookingDistributionMapper bookingDistributionMapper;

  @Autowired
  private TransactionServiceImp transactionServiceImp;

  @Autowired
  private ContractServiceImp contractServiceImp;

  private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);


  private final AppointmentRepository appointmentRepository;
  private final BookingDistributionRepository bookingDistributionRepository;
  private final SubscriptionRepository subscriptionRepository;
  private final ApartmentRepository apartmentRepository;
  private final AgencyRepository agencyRepository;
  private final AccountRepository accountRepository;
  private final InvestorRepository investorRepository;
  private final ContractRepository contractRepository;
  private final CustomerRepository customerRepository;
  private final BuildingRepository buildingRepository;

  public AppointmentService(AppointmentRepository appointmentRepository,
      BookingDistributionRepository bookingDistributionRepository,
      SubscriptionRepository subscriptionRepository,
      ApartmentRepository apartmentRepository,
      AgencyRepository agencyRepository,
      AccountRepository accountRepository,
      InvestorRepository investorRepository,
      ContractRepository contractRepository,
      CustomerRepository customerRepository,
      BuildingRepository buildingRepository) {
    this.appointmentRepository = appointmentRepository;
    this.bookingDistributionRepository = bookingDistributionRepository;
    this.subscriptionRepository = subscriptionRepository;
    this.apartmentRepository = apartmentRepository;
    this.agencyRepository = agencyRepository;
    this.accountRepository = accountRepository;
    this.investorRepository = investorRepository;
    this.contractRepository = contractRepository;
    this.customerRepository = customerRepository;
    this.buildingRepository = buildingRepository;
  }

  @Override
  public List<AppointmentDTO> getAll() {

    return appointmentRepository.findAll().stream().map(appointmentMapper::convertToDTO).collect(
        Collectors.toList());
  }

  @Override
  public AppointmentDTO getAppointmentByBookingDistributionId(Integer distributionId) {
    BookingDistributionEntity bookingDistribution  = bookingDistributionRepository.findById(distributionId).orElseThrow(null);
    AppointmentDTO appointmentDTO = appointmentMapper.convertToDTO(appointmentRepository.findByDistribution(bookingDistribution));
    return appointmentDTO;
  }

  @Override
  public AppointmentDTO changeStatus(Integer appointmentId) {
    AppointmentEntity appointment = appointmentRepository.findById(appointmentId).orElseThrow(null);
    Integer status = appointment.getAppointmentStatus();
    if(status == 0){
      appointment.setAppointmentStatus(1);
    }
    if(status == 1){
      appointment.setAppointmentStatus(0);
    }
    appointmentRepository.save(appointment);
    return appointmentMapper.convertToDTO(appointment);
  }

  @Override
  public AppointmentDTO findByApartmentId(Integer apartId) {
    AppointmentEntity appointment = appointmentRepository.findByApartmentId(apartId);
    AppointmentDTO appointmentDTO = appointmentMapper.convertToDTO(appointment);
    return appointmentDTO;
  }

  @Override
  public AppointmentDTO setMeetingDate(Integer appoindId, Date meetingDate) {
    AppointmentEntity appointment = appointmentRepository.findById(appoindId).orElseThrow(null);
    appointment.setMeetingDate(meetingDate);
    appointmentRepository.save(appointment);
    AppointmentDTO appointmentDTO = appointmentMapper.convertToDTO(appointment);
    return appointmentDTO;
  }

  @Override
  public AppointmentDTO isSold(Integer appointId, Integer subId) {
    try {
      //Tìm appointment
      AppointmentEntity appointment = appointmentRepository.findById(appointId)
          .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));


      //Set Appointment status sang 2(DONE)
      appointment.setAppointmentStatus(2);
      AppointmentDTO appointmentDTO = appointmentMapper.convertToDTO(appointment);

      // Tìm subList
      List<SubscriptionEntity> listSub = subscriptionRepository.findAllByAppointment(appointment);

      for (SubscriptionEntity sub : listSub) {
        // Tìm sub = subId
        if (sub.getId().equals(subId)) {
          // Chuyển status sub sang 0
          sub.setSubscriptionStatus(0);
        } else {
          // Chuyển status các sub còn lại sang 3(đã bị bán)
          sub.setSubscriptionStatus(3);
        }
        subscriptionRepository.save(sub);
      }

      //Chuyển bookingDistribution sang 1(Đã bán)
      BookingDistributionEntity bookingDistribution = bookingDistributionRepository.findById(
          appointment.getDistribution().getId()).orElseThrow(() -> new EntityNotFoundException("BookingDistribution not found"));
      Double bookingFee = bookingDistribution.getBookingFee();
      bookingDistribution.setBookingStatus(1);
      bookingDistributionRepository.save(bookingDistribution);
      //Chuyển apartment sang 0(Đã bán)
      ApartmentEntity apartment = apartmentRepository.findById(
          bookingDistribution.getApartment().getId()).orElseThrow(() -> new EntityNotFoundException("Apartment not found"));
      Double apartmentPrice = apartment.getPrice();
      apartment.setStatus(0);
      apartmentRepository.save(apartment);

      AccountEntity accountAdmin = accountRepository.findById(1).orElseThrow(null);
      Double toAgency = (bookingFee * apartmentPrice) + (0.01 * apartmentPrice);
      Double investorAmount = (apartmentPrice * 0.004 + apartmentPrice * 0.01);
      //Chuyển tiền cho agency(bookingFee * apartmentPrice + 0.4% * apartmentPrice)
      AgencyEntity agency = agencyRepository.findById(bookingDistribution.getAgency().getId())
          .orElseThrow(null);
      AccountEntity accountAgency = accountRepository.findById(agency.getAccount().getId())
          .orElseThrow(null);
      accountAgency.setBalance(accountAgency.getBalance() + toAgency);
      accountRepository.save(accountAgency);
      //Admin trừ tiền của mình
      accountAdmin.setBalance(accountAdmin.getBalance() - toAgency);
      accountRepository.save(accountAdmin);
      //Tạo transaction status = 3
      transactionServiceImp.createBackToAgency(accountAgency.getId(), toAgency);
      //Trừ tiền của investor
      InvestorEntity investor = investorRepository.findByApartmentId(apartment.getId());
      AccountEntity accountInvestor = accountRepository.findById(investor.getAccount().getId())
          .orElseThrow(null);
      accountInvestor.setBalance(accountInvestor.getBalance() - investorAmount);
      accountRepository.save(accountInvestor);
      // Cộng tiền cho admin
      accountAdmin.setBalance(accountAdmin.getBalance() + investorAmount);
      accountRepository.save(accountAdmin);
      //Tạo transaction status = 4
      transactionServiceImp.createInvestorPay(accountInvestor.getId(), investorAmount);

      //tìm customer để lấy data
      SubscriptionEntity subscription = subscriptionRepository.findById(subId).orElseThrow(null);
      CustomerEntity customer = customerRepository.findById(subscription.getCustomer().getId())
          .orElseThrow(null);
      AccountEntity accountCustomer = accountRepository.findById(customer.getAccount().getId())
          .orElseThrow(null);
      BuildingEntity building = buildingRepository.findById(apartment.getBuilding().getId())
          .orElseThrow(null);
      //Tạo contract
      ContractDTO contractDTO = new ContractDTO();
      contractDTO.setAppointmentId(appointment.getId());
      contractDTO.setAmount(apartmentPrice);
      contractDTO.setCustomerName(accountCustomer.getName());
      contractDTO.setAgencyName(accountAgency.getName());
      ZonedDateTime nowGMT7 = ZonedDateTime.now(ZoneId.of("GMT+7"));
      Date dateNow = Date.from(nowGMT7.toInstant());
      contractDTO.setSignDate(dateNow);
      contractDTO.setCity(building.getCity().getCityName());
      contractDTO.setAddress(building.getAddress());
      ContractDTO contractDTO1 = contractServiceImp.createContract(contractDTO);
      logger.info("Appointment sold successfully for appointId: {}, subId: {}", appointId, subId);
      return appointmentDTO;
    } catch (EntityNotFoundException e) {
      logger.error("Entity not found: {}", e.getMessage());
      // Handle the specific case or rethrow as a custom exception
      throw new EntityNotFoundException("Entity not found", e);
    } catch (Exception e) {
      logger.error("Error processing isSold for appointId: {}, subId: {}", appointId, subId, e);
      // Handle or rethrow
      throw new RuntimeException("Error processing isSold", e);
    }
  }
}
