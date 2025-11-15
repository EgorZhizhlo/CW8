package com.leasing.service;

import com.leasing.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client create(Client client);

    List<Client> findAll();

    Optional<Client> findById(Long id);

    Client update(Long id, Client client);

    void delete(Long id);
}
