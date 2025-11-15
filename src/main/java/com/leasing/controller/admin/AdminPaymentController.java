package com.leasing.controller.admin;

import com.leasing.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/payments")
public class AdminPaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("payments", paymentService.findAll());
        return "admin/payments/list";
    }

    @GetMapping("/confirm/{id}")
    public String confirm(@PathVariable Long id) {
        paymentService.confirm(id);
        return "redirect:/admin/payments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        paymentService.delete(id);
        return "redirect:/admin/payments";
    }
}
