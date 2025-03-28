package com.metacoding.storev2.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        // 동일 회원 있는지 검사
        User user = userRepository.findByUsername(joinDTO.getUsername());
        if (user != null) {
            throw new RuntimeException("동일한 username이 존재합니다.");
        }

        // 회원가입
        userRepository.save(joinDTO.getUsername(), joinDTO.getPassword(), joinDTO.getFullname());

    }


    public User 로그인(UserRequest.LoginDTO loginDTO) {
        // User 존재 확인
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if (user == null) {
            throw new RuntimeException("해당 username이 없습니다.");
        }
        if (!(user.getPassword().equals(loginDTO.getPassword()))) {
            throw new RuntimeException("해당 password가 틀렸습니다.");
        }

        // 로그인
        return user;

    }
}
