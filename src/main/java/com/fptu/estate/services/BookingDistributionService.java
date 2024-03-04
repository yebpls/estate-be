package com.fptu.estate.services;

import com.fptu.estate.DTO.AppointmentDTO;
import com.fptu.estate.DTO.BookingDistributionDTO;
import com.fptu.estate.DTO.TransactionDTO;
import com.fptu.estate.entities.AccountEntity;
import com.fptu.estate.entities.AgencyEntity;
import com.fptu.estate.entities.ApartmentEntity;
import com.fptu.estate.entities.BookingDistributionEntity;
import com.fptu.estate.mapper.AppointmentMapper;
import com.fptu.estate.mapper.BookingDistributionMapper;
import com.fptu.estate.repository.AccountRepository;
import com.fptu.estate.repository.AgencyRepository;
import com.fptu.estate.repository.ApartmentRepository;
import com.fptu.estate.repository.AppointmentRepository;
import com.fptu.estate.repository.BookingDistributionRepository;
import com.fptu.estate.services.imp.BookingDistributionServiceImp;
import com.fptu.estate.services.imp.TransactionServiceImp;
import java.util.List;
import java.util.stream.Collectors;
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
      accountRepository.save(account);
      TransactionDTO transactionDTO = transactionServiceImp.createDeposit(account.getId(), deposit);
      BookingDistributionDTO bookingDistributionDTO1 = bookingDistributionMapper.convertToDTO(bookingDistribution);
      return bookingDistributionDTO1;
    } catch (Exception e) {
      throw new RuntimeException("Error create booking distribution " + e.getMessage());
    }
  }
}
