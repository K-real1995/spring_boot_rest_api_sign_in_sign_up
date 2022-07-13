package com.kirillsoklakov.application.repository;

import com.kirillsoklakov.application.models.entity.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//Репозиторий питомцев для связи с бд
@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {

    Optional<Pet> findByName(String name);

    List<Pet> findAllByOwner(String owner);
}
