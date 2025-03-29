package com.metacoding.storev2.order;

import com.metacoding.storev2.store.Store;
import com.metacoding.storev2.store.StoreRepository;
import com.metacoding.storev2.store.StoreRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;


    public List<OrderResponse.OrderListPage> 구매목록() {

        return orderRepository.findAllJoinStore();
    }

    @Transactional
    public void 구매(int storeId, int qty) {
        // 재고조회
        Store store = storeRepository.findById(storeId);

        // 업데이트
        store.재고감소(qty);

        storeRepository.update(store.getId(),store.getName(),store.getStock(),store.getPrice());

        // 구매기록
        orderRepository.save(storeId,qty,qty*store.getPrice());


    }
}
