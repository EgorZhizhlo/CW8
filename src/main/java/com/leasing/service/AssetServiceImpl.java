package com.leasing.service;

import com.leasing.model.Asset;
import com.leasing.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    @Override
    public Asset create(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    @Override
    public Optional<Asset> findById(Long id) {
        return assetRepository.findById(id);
    }

    @Override
    public Asset update(Long id, Asset newAsset) {

        Asset existing = assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found"));

        existing.setName(newAsset.getName());
        existing.setType(newAsset.getType());
        existing.setCost(newAsset.getCost());
        existing.setStatus(newAsset.getStatus());

        return assetRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        assetRepository.deleteById(id);
    }
}
