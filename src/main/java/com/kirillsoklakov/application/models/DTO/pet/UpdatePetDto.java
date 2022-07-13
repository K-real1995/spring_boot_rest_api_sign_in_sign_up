package com.kirillsoklakov.application.models.DTO.pet;

import java.time.LocalDate;

// Data Transfer Object для обновления питомца
public class UpdatePetDto {

    private String name;
    private String gender;
    private LocalDate birthday;
    private String owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
