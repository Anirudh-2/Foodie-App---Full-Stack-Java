package com.project.Food_App.controller;

import com.project.Food_App.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Uncomment when you need to implement the POST method for payment link generation
    /*
    @PostMapping("/{orderId}/payment")
    public ResponseEntity<PaymentResponse> generatePaymentLink(@PathVariable Long orderId) throws StripeException {
        PaymentResponse res = paymentService.generatePaymentLink(orderId);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }
    */
}
