package com.leasing.service;

import com.leasing.model.Payment;
import com.leasing.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Payment update(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void confirm(Long id) {
        paymentRepository.findById(id).ifPresent(payment -> {
            payment.setStatus(Payment.Status.PAID);
            paymentRepository.save(payment);
        });
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}
