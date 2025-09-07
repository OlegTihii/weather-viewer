package org.weather.repository;

import org.weather.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(int id);

    Optional<User> findByLoginAndPassword(User user);

    Optional<User> save(User user);

    void deleteAll();
}
