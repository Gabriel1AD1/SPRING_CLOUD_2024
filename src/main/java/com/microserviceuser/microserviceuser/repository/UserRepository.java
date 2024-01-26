package com.microserviceuser.microserviceuser.repository;

import com.microserviceuser.microserviceuser.models.entitys.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {


}
