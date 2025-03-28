package com.metacoding.storev2.store;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class StoreRepository {
    private final EntityManager em;

    public void save(String name, int stock, int price) {
        Query query = em.createNativeQuery("insert into store_tb (name, stock, price) values (?, ?, ?)");
        query.setParameter(1, name);
        query.setParameter(2, stock);
        query.setParameter(3, price);
        query.executeUpdate();
    }
}
