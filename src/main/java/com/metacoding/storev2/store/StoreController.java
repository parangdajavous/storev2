package com.metacoding.storev2.store;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/store/{id}")
    public String detail(@PathVariable("id") int id, HttpServletRequest request) {
        Store store = storeService.상세보기(id);
        request.setAttribute("model", store);
        return "store/detail";
    }

    @PostMapping("/store/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        storeService.상품삭제(id);
        return "redirect:/store/list";
    }


    @GetMapping("/store/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) {
        Store store = storeService.상세보기(id);
        request.setAttribute("model", store);
        return "store/update-form";
    }

    @PostMapping("/store/{id}/update")
    public String update(@PathVariable("id") int id, StoreRequest.UpdateDTO updateDTO) {
        storeService.상품수정(id, updateDTO);
        return "redirect:/store/" + id;
    }
}
