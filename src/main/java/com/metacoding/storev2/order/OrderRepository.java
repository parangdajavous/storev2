package com.metacoding.storev2.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderRepository {
    private final EntityManager em;

    public List<OrderResponse.OrderListPage> findAllJoinStore() {
        List<OrderResponse.OrderListPage> orderListPages = new ArrayList<>();
        String sql = "select ot.id, st.name, ot.qty, ot.total_price from order_tb ot inner join store_tb st on ot.store_id=st.id order by ot.id desc";
        Query query = em.createNativeQuery(sql);

        List<Object[]> objList = (List<Object[]>) query.getResultList();

        for (Object[] obj : objList) {
            OrderResponse.OrderListPage olp = new OrderResponse.OrderListPage(
                    (Integer) obj[0],
                    (String) obj[1],
                    (Integer) obj[2],
                    (Integer) obj[3]
            );
            orderListPages.add(olp);
        }
        return orderListPages;
    }

    public void save(int storeId, int qty, int totalPrice) {
        Query query = em.createNativeQuery("insert into order_tb(store_id,qty,total_price) values(?,?,?)");
        query.setParameter(1, storeId);
        query.setParameter(2, qty);
        query.setParameter(3, totalPrice);
        query.executeUpdate();
    }
}
