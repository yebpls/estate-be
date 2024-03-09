package com.fptu.estate.services;

import com.fptu.estate.DTO.AppointmentDTO;
import com.fptu.estate.DTO.BookingDistributionDTO;
import com.fptu.estate.DTO.SubscriptionDTO;
import com.fptu.estate.DTO.TransactionDTO;
import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.entities.AgencyEntity;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.AppointmentEntity;
import com.fptu.estate.entities.BookingDistributionEntity;
import com.fptu.estate.entities.InvestorEntity;
import com.fptu.estate.entities.SubscriptionEntity;
import com.fptu.estate.mapper.AppointmentMapper;
import com.fptu.estate.mapper.BookingDistributionMapper;
import com.fptu.estate.repository.AccountRepository;
import com.fptu.estate.repository.AgencyRepository;
import com.fptu.estate.repository.ApartmentRepository;
import com.fptu.estate.repository.AppointmentRepository;
import com.fptu.estate.repository.BookingDistributionRepository;
import com.fptu.estate.repository.InvestorRepository;
import com.fptu.estate.repository.SubscriptionRepository;
import com.fptu.estate.services.imp.BookingDistributionServiceImp;
import com.fptu.estate.services.imp.TransactionServiceImp;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingDistributionService implements BookingDistributionServiceImp {
  @Autowired
  private AgencyRepository agencyRepository;

  @Autowired
  private BookingDistributionRepository bookingDistributionRepository;

  @Autowired
  private BookingDistributionMapper bookingDistributionMapper;
  @Autowired
  private ApartmentRepository apartmentRepository;
  @Autowired
  private AppointmentRepository appointmentRepository;
  @Autowired
  private AppointmentMapper appointmentMapper;
  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private TransactionServiceImp transactionServiceImp;
  @Autowired
  private InvestorRepository investorRepository;
  @Autowired
  private SubscriptionRepository subscriptionRepository;


  private static final Logger logger = LoggerFactory.getLogger(BookingDistributionService.class);


  @Override
  public List<BookingDistributionDTO> getAllByAgencyId(Integer id) {
    AgencyEntity agency = agencyRepository.findById(id).orElseThrow(null);
    return bookingDistributionRepository.findAllByAgency(agency).stream().map(bookingDistributionMapper::convertToDTO).collect(
        Collectors.toList());
  }

  @Override
  public List<BookingDistributionDTO> getAllByStatus(Integer status) {
    return bookingDistributionRepository.findAllByBookingStatus(status).stream().map(bookingDistributionMapper::convertToDTO).collect(
        Collectors.toList());

  }

  @Override
  public boolean cancelBookingDistribution(Integer distributionId) {
    try {
      BookingDistributionEntity bookingDistribution = bookingDistributionRepository.findById(
          distributionId).orElseThrow(null);
      ApartmentEntity apartment = apartmentRepository.findById(
          bookingDistribution.getApartment().getId()).orElseThrow(null);
      InvestorEntity investor = investorRepository.findByApartmentId(
          bookingDistribution.getApartment().getId());
      AppointmentEntity appointment = appointmentRepository.findByDistribution(bookingDistribution);
      AccountEntity account = accountRepository.findById(investor.getAccount().getId())
          .orElseThrow(null);
      List<SubscriptionEntity> listSubscription = subscriptionRepository.findAllByAppointment(
          appointment);
      AccountEntity accountAdmin = accountRepository.findById(1).orElseThrow(null);
      Double backMoney = bookingDistribution.getBookingFee() * apartment.getPrice();
      //Check nếu appointment còn subscription thì ko cho huỷ
      //Nếu appointment status = 1 thì ko cho huỷ
      if (!listSubscription.isEmpty() || appointment.getAppointmentStatus().equals(1)) {
        throw new RuntimeException(
            "Error at cancelBooking: còn nhiều sub hoặc appointment đã có khách hẹn");
      } else {
        //Cộng tiền vào tài khoản investor
        account.setBalance(account.getBalance() + backMoney);
        accountRepository.save(account);
        TransactionDTO transactionDTO = transactionServiceImp.createBackToInvestor(account.getId(), backMoney);
        logger.info("Refunded {} to investor's account for distribution ID: {}", backMoney,
            distributionId);

        //Trừ tiền admin khỏi hệ thống
        accountAdmin.setBalance(accountAdmin.getBalance() - backMoney);
        accountRepository.save(accountAdmin);
        logger.info("Deducted {} from admin account for distribution ID: {}", backMoney,
            distributionId);

        //Xoá list subscription
        subscriptionRepository.deleteAll(listSubscription);
        logger.info("Deleted all subscriptions related to appointment ID: {}", appointment.getId());

        //Xoá appointment
        appointmentRepository.delete(appointment);
        logger.info("Deleted appointment ID: {}", appointment.getId());

        //Xoá bookingDistribution
        bookingDistributionRepository.delete(bookingDistribution);
        logger.info("Deleted booking distribution ID: {}", distributionId);

        //Set apartment Status thành 1
        apartment.setStatus(1);
        apartmentRepository.save(apartment);

        return true;
      }
    } catch (Exception e){
      logger.error("Error cancelling booking distribution for ID: {}. Error: {}", distributionId, e.getMessage(), e);
      return false;
    }

  }

  @Override
  public BookingDistributionDTO createBookingDistribution(BookingDistributionDTO bookingDistributionDTO) {
    BookingDistributionEntity bookingDistribution = bookingDistributionMapper.revertToEntity(bookingDistributionDTO);
    ApartmentEntity apartment = apartmentRepository.findById(bookingDistributionDTO.getApartmentId()).orElseThrow(null) ;
    Integer status = apartment.getStatus();
    if(status != 1){
      throw new RuntimeException("Dự án đã có người tiếp nhận bán hoặc đã bán !!!");
    }
    try {
      apartment.setStatus(2);
      apartmentRepository.save(apartment);
      bookingDistributionRepository.save(bookingDistribution);
      AppointmentDTO appointmentDTO = new AppointmentDTO();
      appointmentDTO.setDistributionId(bookingDistribution.getId());
      appointmentDTO.setUpdateDate(bookingDistribution.getCreateDate());
      appointmentDTO.setMeetingDate(bookingDistribution.getCreateDate());
      appointmentDTO.setAppointmentStatus(0);
      appointmentRepository.save(appointmentMapper.revertToEntity(appointmentDTO));
      Double price = apartment.getPrice();
      Double fee = bookingDistribution.getBookingFee();
      Double deposit = price * fee;
      AgencyEntity agency = agencyRepository.findById(bookingDistributionDTO.getAgencyId()).orElseThrow(null);
      AccountEntity account = accountRepository.findById(agency.getAccount().getId()).orElseThrow(null);
      if(account.getBalance() < deposit) {
        throw new Exception("Account does not have enough balance for this transaction.");
      }
      account.setBalance(account.getBalance() - deposit);
      AccountEntity account1 = accountRepository.findById(1).orElseThrow(null);
      account1.setBalance(account1.getBalance() + deposit);
      accountRepository.save(account);
      accountRepository.save(account1);
      TransactionDTO transactionDTO = transactionServiceImp.createDeposit(account.getId(), deposit);
      BookingDistributionDTO bookingDistributionDTO1 = bookingDistributionMapper.convertToDTO(bookingDistribution);
      return bookingDistributionDTO1;
    } catch (Exception e) {
      throw new RuntimeException("Error create booking distribution " + e.getMessage());
    }
  }
}
