package com.kirillsoklakov.application.models.entity;

import javax.persistence.*;
import java.time.LocalDate;
// Entity для питомцев с геттерами сеттерами и конструктором
@Entity
@Table(name = "pets")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String gender;
    private LocalDate birthday;
    private String owner;

    public Pet() {
    }

    public Pet(String name, String gender, LocalDate birthday, String owner) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public static Pet buildAnimal(String name, String gender, LocalDate birthday, String owner) {
        return new Pet(name, gender, birthday, owner);
    }
}
