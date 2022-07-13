package com.kirillsoklakov.application.service;

import com.kirillsoklakov.application.models.AppException;
import com.kirillsoklakov.application.models.AppResponseCode;
import com.kirillsoklakov.application.models.DTO.pet.AddPetDto;
import com.kirillsoklakov.application.models.DTO.pet.DeletePetDto;
import com.kirillsoklakov.application.models.DTO.pet.UpdatePetDto;
import com.kirillsoklakov.application.models.entity.Pet;
import com.kirillsoklakov.application.repository.PetRepository;
import org.springframework.stereotype.Service;


import java.util.List;
//Имплементация сервиса питомцев с реализацией методов
@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }
    //Добавление питомца
    public Pet add(AddPetDto animalDto) {
        if (petRepository.findByName(animalDto.getName()).isPresent()) {
            throw new AppException(AppResponseCode.PET_ALREADY_EXISTS);
        }
        Pet pet = Pet.buildAnimal(animalDto.getName(), animalDto.getGender(), animalDto.getBirthday(), animalDto.getOwner());
        return petRepository.save(pet);
    }
    //Обновление данных питомца
    public Pet update(UpdatePetDto animalDto) {
        Pet pet = petRepository.findByName(animalDto.getName()).orElseThrow(() -> new AppException(AppResponseCode.PET_IS_NOT_FOUND));
        if (!pet.getOwner().equals(animalDto.getOwner())) {
            throw new AppException(AppResponseCode.PET_HAS_ANOTHER_OWNER_ACCSESS_DENIED);
        }
        pet.setGender(pet.getGender());
        pet.setBirthday(animalDto.getBirthday());
        petRepository.save(pet);
        return pet;
    }
    //Удаление питомца
    public Pet delete(DeletePetDto animalDto) {
        Pet pet = petRepository.findByName(animalDto.getName()).orElseThrow(() -> new AppException(AppResponseCode.PET_IS_NOT_FOUND));
        if (!pet.getOwner().equals(animalDto.getOwner())) {
            throw new AppException(AppResponseCode.PET_HAS_ANOTHER_OWNER_ACCSESS_DENIED);
        }
        petRepository.delete(pet);
        return pet;
    }
    //Получение списка питомцев пользователя
    public List<Pet> getAllPetsByUser(String username) {
        return petRepository.findAllByOwner(username);
    }
    //Получение питомца по id
    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new AppException(AppResponseCode.PET_IS_NOT_FOUND));
    }
}
