package com.metacoding.storev2.order;

import com.metacoding.storev2.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final OrderService orderService;
    private final HttpSession session;

    @GetMapping("/order/list")
    public String orderList(HttpServletRequest request) {
        // 인증체크 (반복되는 공통부가로직)
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("로그인 후 사용해주세요");

        List<OrderResponse.OrderListPage> OrderListPage = orderService.구매목록();
        request.setAttribute("models", OrderListPage);
        return "order/list";
    }

    @PostMapping("order/save")
    public String save(@RequestParam("storeId") int storeId, @RequestParam("qty") int qty) {
        // 인증체크 (반복되는 공통부가로직)
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) throw new RuntimeException("로그인 후 사용해주세요");

        orderService.구매(storeId,qty);
        return "redirect:/order/list";
    }

}
