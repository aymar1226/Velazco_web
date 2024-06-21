package com.utp.spring.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.utp.spring.models.dao.ICarritoDao;
import com.utp.spring.models.dto.CarritoDTO;
import com.utp.spring.models.dto.PaymentDTO;
import com.utp.spring.models.entity.Carrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements IPaymentService{

    @Autowired
    private ICarritoDao carritoDao;

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Override
    public PaymentIntent paymentIntent(String correo) throws StripeException {
        Stripe.apiKey=stripeApiKey;
        Optional<Carrito> carrito = carritoDao.findByEmail(correo);
        if(carrito.isPresent()){
            PaymentDTO paymentDTO = new PaymentDTO();

            // Convertir el total del carrito (double) a centavos (long)
            double totalCarrito = carrito.get().getTotal();
            int amountInCents = (int) (totalCarrito * 100);

            paymentDTO.setAmount(amountInCents);
            paymentDTO.setDescripcion("Payment for cart id: " + carrito.get().getId());

            Map<String,Object> params = new HashMap<>();
            params.put("amount",paymentDTO.getAmount());
            params.put("currency","pen");
            params.put("description",paymentDTO.getDescripcion());

            ArrayList payment_method_types = new ArrayList<>();
            payment_method_types.add("card");
            params.put("payment_method_types",payment_method_types);

            return PaymentIntent.create(params);
        }
        throw new RuntimeException("No se pudo encontrar el carrito del usuario");
    }

    @Override
    public PaymentIntent confirm(String id, String paymentMethodId) throws StripeException {
        Stripe.apiKey = stripeApiKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", paymentMethodId);
        return paymentIntent.confirm(params);
    }

    @Override
    public PaymentIntent cancel(String id) throws StripeException {
        Stripe.apiKey=stripeApiKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        paymentIntent.cancel();
        return paymentIntent;
    }


}
