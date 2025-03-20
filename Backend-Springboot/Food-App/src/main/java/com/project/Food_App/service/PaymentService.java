package com.project.Food_App.service;

import com.project.Food_App.Model.Order;
import com.project.Food_App.Model.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {
     PaymentResponse generatePaymentLink(Order order) throws StripeException;

}
