package com.metacoding.storev2.store;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/store/save-form")
    public String saveForm() {
        return "store/save-form";
    }

    @PostMapping("/store/save")
    public String save(StoreRequest.SaveDTO saveDTO) {
        System.out.println("name: " + saveDTO.getName());
        System.out.println("stock: " + saveDTO.getStock());
        System.out.println("price: " + saveDTO.getPrice());
        storeService.상품등록(saveDTO);
        return "redirect:/";
    }

    @GetMapping("/store/list")
    public String list(HttpServletRequest request) {
        List<Store> storeList = storeService.상품목록();
        request.setAttribute("models", storeList);
        return "store/list";
    }
}
