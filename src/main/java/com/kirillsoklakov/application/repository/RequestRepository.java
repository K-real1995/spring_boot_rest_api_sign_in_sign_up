package com.kirillsoklakov.application.repository;

import com.kirillsoklakov.application.models.entity.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
//Репозиторий запросов для связи с бд
@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {

    List<Request> findAllByIp(String ip);
}
