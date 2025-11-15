package com.leasing.controller.admin;

import com.leasing.model.News;
import com.leasing.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/news")
public class AdminNewsController {

    private final NewsService newsService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("news", newsService.findAll());
        return "admin/news/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("newsItem", new News());
        return "admin/news/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute News news) {
        news.setCreatedAt(LocalDateTime.now());
        newsService.create(news);
        return "redirect:/admin/news";
    }

    @GetMapping("/update/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("newsItem", newsService.findById(id).orElse(null));
        return "admin/news/form";
    }

    @PostMapping("/update/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute News news) {
        newsService.update(id, news);
        return "redirect:/admin/news";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        newsService.delete(id);
        return "redirect:/admin/news";
    }
}
