package com.leasing.service;

import com.leasing.model.Request;

import java.util.List;
import java.util.Optional;

public interface RequestService {

    Request create(Request request);

    List<Request> findAll();

    Optional<Request> findById(Long id);

    void markProcessed(Long id);

    void delete(Long id);
}
