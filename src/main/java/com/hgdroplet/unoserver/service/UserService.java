package com.hgdroplet.unoserver.service;

import com.hgdroplet.unoserver.domain.User;
import com.hgdroplet.unoserver.repository.MemoryUserRepository;
import com.hgdroplet.unoserver.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository = new MemoryUserRepository();

    public Long join(User user) {
        validateDuplicateNickname(user);

        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateNickname(User user) {
        userRepository.findByNickname(user.getNickname()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        });
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }
}
