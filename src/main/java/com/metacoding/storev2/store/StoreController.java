package com.metacoding.storev2.store;

import com.metacoding.storev2.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class StoreController {
    private final StoreService storeService;
    private final HttpSession session;

    @GetMapping("/store/save-form")
    public String saveForm() {
        return "store/save-form";
    }

    @PostMapping("/store/save")
    public String save(StoreRequest.SaveDTO saveDTO) {
        // 인증체크 (반복되는 공통부가로직)
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("로그인 후 사용해주세요");

        System.out.println("name: " + saveDTO.getName());
        System.out.println("stock: " + saveDTO.getStock());
        System.out.println("price: " + saveDTO.getPrice());
        storeService.상품등록(saveDTO);
        return "redirect:/store/list";
    }

    @GetMapping("/store/list")
    public String list(HttpServletRequest request) {
        // 인증체크 (반복되는 공통부가로직)
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("로그인 후 사용해주세요");

        List<Store> storeList = storeService.상품목록();
        request.setAttribute("models", storeList);
        return "store/list";
    }

    @GetMapping("/store/{id}")
    public String detail(@PathVariable("id") int id, HttpServletRequest request) {
        // 인증체크 (반복되는 공통부가로직)
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("로그인 후 사용해주세요");

        Store store = storeService.상세보기(id);
        request.setAttribute("model", store);
        return "store/detail";
    }

    @PostMapping("/store/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        // 인증체크 (반복되는 공통부가로직)
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("로그인 후 사용해주세요");

        storeService.상품삭제(id);
        return "redirect:/store/list";
    }


    @GetMapping("/store/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) {
        // 인증체크 (반복되는 공통부가로직)
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("로그인 후 사용해주세요");

        Store store = storeService.상세보기(id);
        request.setAttribute("model", store);
        return "store/update-form";
    }

    @PostMapping("/store/{id}/update")
    public String update(@PathVariable("id") int id, @RequestParam("name")String name,@RequestParam("stock") int stock,@RequestParam("price") int price) {
        // 인증체크 (반복되는 공통부가로직)
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("로그인 후 사용해주세요");

        storeService.상품수정(id,name, stock, price);
        return "redirect:/store/" + id;
    }
}
