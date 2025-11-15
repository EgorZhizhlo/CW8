package com.leasing.controller.admin;

import com.leasing.model.Contract;
import com.leasing.service.ClientService;
import com.leasing.service.AssetService;
import com.leasing.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/contracts")
public class AdminContractController {

    private final ContractService contractService;
    private final ClientService clientService;
    private final AssetService assetService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("contracts", contractService.findAll());
        return "admin/contracts/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("contract", new Contract());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("assets", assetService.findAll());
        return "admin/contracts/form";
    }

    @PostMapping("/create")
    public String create(@RequestParam Long clientId,
                         @RequestParam Long assetId,
                         @ModelAttribute Contract contract) {
        contractService.create(contract, clientId, assetId);
        return "redirect:/admin/contracts";
    }

    @GetMapping("/update/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("contract", contractService.findById(id).orElse(null));
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("assets", assetService.findAll());
        return "admin/contracts/form";
    }

    @PostMapping("/update/{id}")
    public String edit(
            @PathVariable Long id,
            @RequestParam Long clientId,
            @RequestParam Long assetId,
            @ModelAttribute Contract contract) {
        contractService.update(id, contract, clientId, assetId);
        return "redirect:/admin/contracts";
    }

    @GetMapping("/close/{id}")
    public String close(@PathVariable Long id) {
        contractService.close(id);
        return "redirect:/admin/contracts";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        contractService.delete(id);
        return "redirect:/admin/contracts";
    }
}
