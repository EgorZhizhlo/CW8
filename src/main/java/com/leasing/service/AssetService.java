package com.leasing.service;

import com.leasing.model.Asset;

import java.util.List;
import java.util.Optional;

public interface AssetService {

    Asset create(Asset asset);

    List<Asset> findAll();

    Optional<Asset> findById(Long id);

    Asset update(Long id, Asset asset);

    void delete(Long id);
}
