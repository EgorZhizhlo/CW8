package com.leasing.service;

import com.leasing.model.News;

import java.util.List;
import java.util.Optional;

public interface NewsService {

    News create(News news);

    List<News> findAll();

    Optional<News> findById(Long id);

    News update(Long id, News news);

    void delete(Long id);
}
