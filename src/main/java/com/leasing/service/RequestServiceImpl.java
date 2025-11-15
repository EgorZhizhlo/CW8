package com.leasing.service;

import com.leasing.model.Request;
import com.leasing.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    @Override
    public Request create(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public Optional<Request> findById(Long id) {
        return requestRepository.findById(id);
    }

    @Override
    public void markProcessed(Long id) {
        requestRepository.findById(id).ifPresent(r -> {
            r.setProcessed(true);
            requestRepository.save(r);
        });
    }

    @Override
    public void delete(Long id) {
        requestRepository.deleteById(id);
    }
}
