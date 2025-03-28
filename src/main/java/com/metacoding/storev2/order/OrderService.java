package com.metacoding.storev2.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderResponse.OrderListPage> 구매목록() {
        return orderRepository.findAllJoinStore();
    }
}
