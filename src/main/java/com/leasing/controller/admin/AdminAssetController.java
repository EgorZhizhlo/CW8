package com.leasing.controller.admin;

import com.leasing.model.Asset;
import com.leasing.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/assets")
public class AdminAssetController {

    private final AssetService assetService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("assets", assetService.findAll());
        return "admin/assets/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("asset", new Asset());
        return "admin/assets/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Asset asset) {
        assetService.create(asset);
        return "redirect:/admin/assets";
    }

    @GetMapping("/update/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("asset", assetService.findById(id).orElse(null));
        return "admin/assets/form";
    }

    @PostMapping("/update/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Asset asset) {

        assetService.update(id, asset);
        return "redirect:/admin/assets";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        assetService.delete(id);
        return "redirect:/admin/assets";
    }
}
