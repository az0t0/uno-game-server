package com.hgdroplet.unoserver.repository;

import com.hgdroplet.unoserver.domain.User;
import jakarta.persistence.EntityManager;

import java.util.*;

public class MariaDBUserRepository implements UserRepository {
    private static long sequence = 0L;
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        user.setId(++sequence);
        entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return Optional.ofNullable(entityManager.find(User.class, nickname));
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select m from User m", User.class).getResultList();
    }
}
