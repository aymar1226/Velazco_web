package com.utp.spring.services;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.utp.spring.models.dto.CarritoDTO;
import com.utp.spring.models.dto.PaymentDTO;

public interface IPaymentService {
    public PaymentIntent paymentIntent (String correo) throws StripeException;

    public PaymentIntent confirm(String id, String paymentMethodId) throws StripeException;
    public PaymentIntent cancel(String id) throws StripeException;
}
