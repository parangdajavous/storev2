package com.metacoding.storev2.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class StoreController {
    private final StoreService storeService;

}
