package com.utp.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.utp.spring.models.dto.CarritoDTO;
import com.utp.spring.models.dto.PaymentConfirmationRequest;
import com.utp.spring.models.dto.PaymentDTO;
import com.utp.spring.models.entity.Carrito;
import com.utp.spring.security.JWTUtils;
import com.utp.spring.services.IPaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @PostMapping("/intent")
    public ResponseEntity<Map<String, String>> payment(HttpServletRequest request) throws StripeException {
        String correo = jwtUtils.extractEmailFromRequest(request);
        PaymentIntent paymentIntent = paymentService.paymentIntent(correo);
        String paymentId = paymentIntent.getId();  // Obtén el id del PaymentIntent

        Map<String, String> response = new HashMap<>();
        response.put("clientSecret", paymentIntent.getClientSecret());
        //response.put("paymentId", paymentId);

        return new ResponseEntity<>(response, HttpStatus.OK);  // Devuelve el id en formato JSON
    }


    @PostMapping("/confirm/{id}")
    public ResponseEntity<String> confirm(@PathVariable String id, @RequestBody PaymentConfirmationRequest request) throws StripeException {
        System.out.println("Confirming payment for intent: " + id);
        PaymentIntent paymentIntent = paymentService.confirm(id, request.getPaymentMethodId());
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancel(@PathVariable String id) throws StripeException {
        PaymentIntent paymentIntent=paymentService.cancel(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

}