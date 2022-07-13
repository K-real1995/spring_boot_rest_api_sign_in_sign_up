package com.kirillsoklakov.application.repository;

import com.kirillsoklakov.application.models.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
//Репозиторий пользователей для связи с бд
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
