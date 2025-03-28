package com.metacoding.storev2.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @Transactional
    public void 상품등록(StoreRequest.SaveDTO saveDTO) {
        storeRepository.save(saveDTO.getName(), saveDTO.getStock(), saveDTO.getPrice());
    }
}
