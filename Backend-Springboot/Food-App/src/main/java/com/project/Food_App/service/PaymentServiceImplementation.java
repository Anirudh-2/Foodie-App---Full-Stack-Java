package com.project.Food_App.service;

import com.project.Food_App.Model.Order;
import com.project.Food_App.Model.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImplementation implements PaymentService {

    //@Value("${stripe.api.key}")
    @Value("${jwt.secret}")
    private String stripeSecretKey;

    @Override
    public PaymentResponse generatePaymentLink(Order order) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        long totalAmountInCents = (long) (order.getTotalAmount() * 100);

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("https://food.vercel.app/payment/success/" + order.getId())
                .setCancelUrl("https://food.vercel.app/cancel")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount(totalAmountInCents)
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("pizza burger")
                                        .build())
                                .build())
                        .build())
                .build();

        Session session = Session.create(params);

        PaymentResponse res = new PaymentResponse();
        res.setPayment_url(session.getUrl());

        return res;
    }
}
