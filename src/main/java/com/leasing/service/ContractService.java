package com.leasing.service;

import com.leasing.model.Contract;

import java.util.List;
import java.util.Optional;

public interface ContractService {

    Contract create(Contract contract, Long clientId, Long assetId);

    Contract update(Long id, Contract contract, Long clientId, Long assetId);

    List<Contract> findAll();

    Optional<Contract> findById(Long id);

    void close(Long id);

    void delete(Long id);
}
