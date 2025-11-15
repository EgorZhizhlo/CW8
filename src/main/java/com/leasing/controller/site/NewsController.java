package com.leasing.controller.site;

import com.leasing.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("newsList", newsService.findAll());
        return "site/news";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        var news = newsService.findById(id).orElse(null);
        model.addAttribute("news", news);
        return "site/news_detail";
    }
}
