package com.leasing.service;

import com.leasing.model.Client;
import com.leasing.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client update(Long id, Client newClient) {

        Client existing = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        existing.setFullName(newClient.getFullName());
        existing.setPhone(newClient.getPhone());
        existing.setEmail(newClient.getEmail());
        existing.setOrganization(newClient.getOrganization());

        return clientRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
