package com.kirillsoklakov.application.service;



import com.kirillsoklakov.application.models.DTO.pet.AddPetDto;
import com.kirillsoklakov.application.models.DTO.pet.DeletePetDto;
import com.kirillsoklakov.application.models.DTO.pet.UpdatePetDto;
import com.kirillsoklakov.application.models.entity.Pet;

import java.util.List;
//Интерфейс сервиса питомцев с используемыми методами
public interface PetService {

    Pet add(AddPetDto animal);

    Pet update(UpdatePetDto animal);

    Pet delete(DeletePetDto animal);

    List<Pet> getAllPetsByUser(String username);

    Pet getPetById(Long id);

}
