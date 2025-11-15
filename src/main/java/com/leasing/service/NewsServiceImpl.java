package com.leasing.service;

import com.leasing.model.News;
import com.leasing.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    public News create(News news) {
        return newsRepository.save(news);
    }

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public Optional<News> findById(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public News update(Long id, News newNews) {

        News existing = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));

        existing.setTitle(newNews.getTitle());
        existing.setContent(newNews.getContent());
        existing.setImageUrl(newNews.getImageUrl());

        return newsRepository.save(existing);
    }


    @Override
    public void delete(Long id) {
        newsRepository.deleteById(id);
    }
}
