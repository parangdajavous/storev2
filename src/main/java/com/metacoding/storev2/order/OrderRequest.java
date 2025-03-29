package com.metacoding.storev2.order;

import lombok.Data;

public class OrderRequest {

    @Data
    public static class SaveDTO{
        Integer storeId;
        Integer qty;
    }
}
