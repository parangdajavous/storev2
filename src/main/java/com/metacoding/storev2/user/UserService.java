package com.metacoding.storev2.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        // 1. 동일 회원 있는지 검사
        User user = userRepository.findByUsername(joinDTO.getUsername());
        if (user != null) {
            throw new RuntimeException("동일한 username이 존재합니다.");
        }

        // 2. 회원가입
        userRepository.save(joinDTO.getUsername(), joinDTO.getPassword(), joinDTO.getFullname());

    }

    
}
