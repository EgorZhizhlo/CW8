package com.leasing.controller.admin;

import com.leasing.model.Client;
import com.leasing.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/clients")
public class AdminClientController {

    private final ClientService clientService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clients", clientService.findAll());
        return "admin/clients/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("client", new Client());
        return "admin/clients/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Client client) {
        client.setCreatedAt(LocalDateTime.now());
        clientService.create(client);
        return "redirect:/admin/clients";
    }

    @GetMapping("/update/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.findById(id).orElse(null));
        return "admin/clients/form";
    }

    @PostMapping("/update/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Client client) {
        clientService.update(id, client);
        return "redirect:/admin/clients";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        clientService.delete(id);
        return "redirect:/admin/clients";
    }
}
