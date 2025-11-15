package com.leasing.service;

import com.leasing.model.Contract;
import com.leasing.model.Payment;
import com.leasing.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final PaymentService paymentService;
    private final ClientService clientService;
    private final AssetService assetService;

    @Override
    public Contract create(Contract contract, Long clientId, Long assetId) {
        contract.setClient(clientService.findById(clientId).orElseThrow());
        contract.setAsset(assetService.findById(assetId).orElseThrow());

        Contract saved = contractRepository.save(contract);

        // создаём платеж
        Payment payment = Payment.builder()
                .contract(saved)
                .paymentDate(saved.getStartDate())
                .amount(saved.getAmount())
                .status(Payment.Status.PENDING)
                .build();

        paymentService.create(payment);

        return saved;
    }

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Optional<Contract> findById(Long id) {
        return contractRepository.findById(id);
    }

    @Override
    public Contract update(Long id, Contract newContract, Long clientId, Long assetId) {

        Contract existing = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        existing.setClient(clientService.findById(clientId).orElseThrow());
        existing.setAsset(assetService.findById(assetId).orElseThrow());
        existing.setStartDate(newContract.getStartDate());
        existing.setEndDate(newContract.getEndDate());
        existing.setAmount(newContract.getAmount());
        existing.setStatus(newContract.getStatus());

        return contractRepository.save(existing);
    }

    @Override
    public void close(Long id) {
        contractRepository.findById(id).ifPresent(contract -> {
            contract.setStatus(Contract.Status.CLOSED);
            contractRepository.save(contract);
        });
    }

    @Override
    public void delete(Long id) {
        contractRepository.deleteById(id);
    }
}
