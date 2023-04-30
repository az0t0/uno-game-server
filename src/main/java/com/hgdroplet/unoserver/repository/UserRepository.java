package com.hgdroplet.unoserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.hgdroplet.unoserver.domain.User;

@Repository
public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByNickname(String nickname);
    // Optional<User> findByPassword(String password);
    List<User> findAll();
}
