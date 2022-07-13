package com.kirillsoklakov.application.controller;

import com.kirillsoklakov.application.models.DTO.pet.AddPetDto;
import com.kirillsoklakov.application.models.DTO.pet.DeletePetDto;
import com.kirillsoklakov.application.models.DTO.pet.UpdatePetDto;
import com.kirillsoklakov.application.models.entity.Pet;
import com.kirillsoklakov.application.models.entity.OAuthAccessToken;
import com.kirillsoklakov.application.payload.MessageResponse;
import com.kirillsoklakov.application.service.PetService;
import com.kirillsoklakov.application.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
//Создаем класс с питомцами с базовым request /pet
@Controller
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;
    private final TokenService tokenService;

    public PetController(PetService petService, TokenService tokenService) {
        this.petService = petService;
        this.tokenService = tokenService;
    }
    //Get запрос на вывод всех питомцев авторизированного пользователя
    @GetMapping("/get-all-pets")
    public ResponseEntity<List<Pet>> getAllPetsByUser(HttpServletRequest request) {
        OAuthAccessToken token = tokenService.getTokenFromRequest(request);
        String username = token.getUsername();
        return ResponseEntity.ok(petService.getAllPetsByUser(username));
    }
    //Get запрос на вывод питомца по его id
    @GetMapping("get-pet-by-id/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }
    //Post запрос на добавление питомца
    @PostMapping("/add-pet")
    public ResponseEntity<?> addPet(HttpServletRequest request, @RequestBody AddPetDto addPetDto) {
        if (addPetDto.getOwner() == null || addPetDto.getOwner().isEmpty() || addPetDto.getOwner().equals("")) {
            OAuthAccessToken token = tokenService.getTokenFromRequest(request);
            String username = token.getUsername();
            addPetDto.setOwner(username);
        }
        petService.add(addPetDto);
        return ResponseEntity.ok(new MessageResponse("Pet with name: " + addPetDto.getName()
                + ", birthday: " + addPetDto.getBirthday() + ", gender: " + addPetDto.getGender()
                + ", owner: " + addPetDto.getOwner() + " was added"));
    }
    //Put запрос на обновление данных о питомце
    @PutMapping("/update-pet")
    public ResponseEntity<Pet> updatePet(HttpServletRequest request, @RequestBody UpdatePetDto updatePetDto) {
        if (updatePetDto.getOwner() == null || updatePetDto.getOwner().isEmpty() || updatePetDto.getOwner().equals("")) {
            OAuthAccessToken token = tokenService.getTokenFromRequest(request);
            String username = token.getUsername();
            updatePetDto.setOwner(username);
        }
        return ResponseEntity.ok(petService.update(updatePetDto));
    }
    //Delete запрос на удаление питомца
    @DeleteMapping("delete-pet")
    public ResponseEntity<?> deletePet(HttpServletRequest request, @RequestBody DeletePetDto deletePetDto) {
        if (deletePetDto.getOwner() == null || deletePetDto.getOwner().isEmpty() || deletePetDto.getOwner().equals("")) {
            OAuthAccessToken token = tokenService.getTokenFromRequest(request);
            String username = token.getUsername();
            deletePetDto.setOwner(username);
        }
        petService.delete(deletePetDto);

        return ResponseEntity.ok(new MessageResponse("Pet with name: " + deletePetDto.getName() + " was deleted"));
    }
}
