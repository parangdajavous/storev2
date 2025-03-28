package com.metacoding.storev2.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @Transactional
    public void 상품등록(StoreRequest.SaveDTO saveDTO) {
        storeRepository.save(saveDTO.getName(), saveDTO.getStock(), saveDTO.getPrice());
    }

    public List<Store> 상품목록() {
        List<Store> storeList = storeRepository.findAll();
        return storeList;
    }

    public Store 상세보기(int id) {
        Store store = storeRepository.findById(id);
        return store;
    }

    @Transactional
    public void 상품삭제(int id) {
        // 상품존재확인
        Store store = storeRepository.findById(id);
        if (store == null) {
            throw new RuntimeException("삭제할 상품이 없습니다.");
        }
        storeRepository.deleteById(id);
    }

    @Transactional
    public void 상품수정(int id, StoreRequest.UpdateDTO updateDTO) {
        // 상품존재확인
        Store store = storeRepository.findById(id);
        if (store == null) {
            throw new RuntimeException("수정할 상품이 없습니다.");
        }

        storeRepository.update(id, updateDTO);
    }
}
