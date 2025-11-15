package com.leasing.controller.admin;

import com.leasing.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminDashboardController {

    private final ClientService clientService;
    private final ContractService contractService;
    private final RequestService requestService;
    private final PaymentService paymentService;
    private final AssetService assetService;
    private final UserService userService;

    @GetMapping("/admin")
    public String dashboard(Model model) {

        long clientsCount = clientService.findAll().size();
        long activeContracts = contractService.findAll()
                .stream()
                .filter(c -> c.getStatus() == com.leasing.model.Contract.Status.ACTIVE)
                .count();
        long pendingPayments = paymentService.findAll()
                .stream()
                .filter(p -> p.getStatus() == com.leasing.model.Payment.Status.PENDING)
                .count();
        long assetsCount = assetService.findAll().size();
        long requestsCount = requestService.findAll().size();
        long usersCount = userService.findAll().size();

        model.addAttribute("clientsCount", clientsCount);
        model.addAttribute("activeContracts", activeContracts);
        model.addAttribute("pendingPayments", pendingPayments);
        model.addAttribute("assetsCount", assetsCount);
        model.addAttribute("requestsCount", requestsCount);
        model.addAttribute("usersCount", usersCount);

        return "admin/dashboard";
    }
}
