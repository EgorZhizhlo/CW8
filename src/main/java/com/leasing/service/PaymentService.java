package com.leasing.service;

import com.leasing.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Payment create(Payment payment);

    List<Payment> findAll();

    Optional<Payment> findById(Long id);

    Payment update(Payment payment);

    void confirm(Long id); // <-- подтверждение админом

    void delete(Long id);
}
