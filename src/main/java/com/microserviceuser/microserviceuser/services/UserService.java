package com.microserviceuser.microserviceuser.services;

import com.microserviceuser.microserviceuser.models.entitys.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> listAll();

    Optional<User> findById(Long id);

    User save(User user);

    void deleteById(Long id);



}
