package com.metacoding.storev2.order;

import lombok.Data;

public class OrderResponse {

    @Data
    public static class OrderListPage {
        private Integer id;
        private String name;
        private Integer qty;
        private Integer totalPrice;

        public OrderListPage(Integer id, String name, Integer qty, Integer totalPrice) {
            this.id = id;
            this.name = name;
            this.qty = qty;
            this.totalPrice = totalPrice;
        }
    }
}
