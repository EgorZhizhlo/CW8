package com.leasing.controller.site;

import com.leasing.model.Request;
import com.leasing.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/request")
    public String submitRequest(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam(required = false) String message,
            Model model
    ) {
        Request request = Request.builder()
                .name(name)
                .phone(phone)
                .message(message)
                .createdAt(LocalDateTime.now())
                .processed(false)
                .build();

        requestService.create(request);

        model.addAttribute("success", true);
        return "site/request_success";
    }
}
