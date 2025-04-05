package org.weather.repository;

import org.weather.entity.Location;
import org.weather.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(int id);

    Optional<User> findByLogin(String name);

    Optional<User> saveUser(User user);

}
