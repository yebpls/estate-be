package com.fptu.estate.controller;

import com.fptu.estate.DTO.PaymentRestDTO;
import com.fptu.estate.DTO.TransactionDTO;
import com.fptu.estate.config.ConfigVNPay;
import com.fptu.estate.services.imp.AccountServiceImp;
import com.fptu.estate.services.imp.TransactionServiceImp;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

  @Autowired
  AccountServiceImp accountServiceImp;

  @Autowired
  TransactionServiceImp transactionServiceImp;

  @PostMapping("/create_payment/{amount}")
  public ResponseEntity<?> createPayment(HttpServletRequest req, HttpServletResponse res,
      @PathVariable("amount") long amount) throws UnsupportedEncodingException {

    String orderType = "other";
//    long amount = Integer.parseInt(req.getParameter("amount"))*100;
//    String bankCode = req.getParameter("bankCode");

    ConfigVNPay Config;
    String vnp_TxnRef = ConfigVNPay.getRandomNumber(8);
    String vnp_IpAddr = ConfigVNPay.getIpAddress(req);
    String vnp_TmnCode = ConfigVNPay.vnp_TmnCode;

    Map<String, String> vnp_Params = new HashMap<>();
    vnp_Params.put("vnp_Version", ConfigVNPay.vnp_Version);
    vnp_Params.put("vnp_Command", ConfigVNPay.vnp_Command);
    vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
    vnp_Params.put("vnp_Amount", String.valueOf(amount * 100));
    vnp_Params.put("vnp_CurrCode", "VND");
    vnp_Params.put("vnp_BankCode", "NCB");
    vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
    vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
    vnp_Params.put("vnp_Locale", "vn");
    vnp_Params.put("vnp_OrderType", orderType);
    vnp_Params.put("vnp_ReturnUrl", ConfigVNPay.vnp_ReturnUrl);
    vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

    Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    String vnp_CreateDate = formatter.format(cld.getTime());
    vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

    cld.add(Calendar.MINUTE, 15);
    String vnp_ExpireDate = formatter.format(cld.getTime());
    vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

    List fieldNames = new ArrayList(vnp_Params.keySet());
    Collections.sort(fieldNames);
    StringBuilder hashData = new StringBuilder();
    StringBuilder query = new StringBuilder();
    Iterator itr = fieldNames.iterator();
    while (itr.hasNext()) {
      String fieldName = (String) itr.next();
      String fieldValue = (String) vnp_Params.get(fieldName);
      if ((fieldValue != null) && (fieldValue.length() > 0)) {
        //Build hash data
        hashData.append(fieldName);
        hashData.append('=');
        hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
        //Build query
        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
        query.append('=');
        query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
        if (itr.hasNext()) {
          query.append('&');
          hashData.append('&');
        }
      }
    }
    String queryUrl = query.toString();
    String vnp_SecureHash = ConfigVNPay.hmacSHA512(ConfigVNPay.secretKey, hashData.toString());
    queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
    String paymentUrl = ConfigVNPay.vnp_PayUrl + "?" + queryUrl;
    PaymentRestDTO paymentRestDTO = new PaymentRestDTO();
    paymentRestDTO.setStatus("Ok");
    paymentRestDTO.setMessage("Successfully");
    paymentRestDTO.setURL(paymentUrl);
//    res.setDateHeader("HEADER_START" ,vnp_CreateDate);
    return ResponseEntity.status(HttpStatus.OK).body(paymentRestDTO);
  }


  @PostMapping("/set-payment/{accId}/{amount}")
  public ResponseEntity<?> createPayment(@PathVariable("accId") Integer accId,
      @PathVariable("amount") Double amount) {
    boolean isOk = accountServiceImp.updatePayment(accId, amount);
    if(isOk){
      TransactionDTO transactionDTO = transactionServiceImp.createRecharge(accId, amount);
      return new ResponseEntity<>("Update payment successfully!", HttpStatus.OK);
    }else{
      return new ResponseEntity<>("Update payment fail!", HttpStatus.BAD_REQUEST);
    }
//    try {
//      accountServiceImp.updatePayment(accId, amount);
//      return new ResponseEntity<>("Update payment successfully!", HttpStatus.OK);
//    } catch (Exception e) {
//      return new ResponseEntity<>("Update payment fail!", HttpStatus.BAD_REQUEST);
//
//    }
  }
}
