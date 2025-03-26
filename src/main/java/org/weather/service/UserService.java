package org.weather.service;

public interface UserService {

    boolean login(String username, String password);

    boolean registration(String username, String password, String repeatPassword);

}
