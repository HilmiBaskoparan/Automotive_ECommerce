package com.hilmibaskoparan.business.abstracts;

import com.hilmibaskoparan.business.requests.paymentRequest.PaymentRequest;
import org.springframework.http.ResponseEntity;

public interface PaymentService {

    public ResponseEntity<?> checkout(PaymentRequest paymentRequest, String ip);

    public  ResponseEntity<?>  retrieveCheckoutRequest(String token, String discountCode);

}
