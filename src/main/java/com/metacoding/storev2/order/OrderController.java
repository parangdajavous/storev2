package com.metacoding.storev2.order;

import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping("/order/list")
    public String orderList(HttpServletRequest request) {
        List<OrderResponse.OrderListPage> OrderListPage = orderService.구매목록();
        request.setAttribute("models", OrderListPage);
        return "order/list";
    }

    @PostMapping("order/save")
    public String save(@RequestParam("storeId") int storeId, @RequestParam("qty") int qty) {
        orderService.구매(storeId,qty);
        return "redirect:/order/list";
    }

}
