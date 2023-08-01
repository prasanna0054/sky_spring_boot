package com.sky.SpringDemo.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = false, nullable = true)
    @NotBlank
    private String name;
    @Column(unique = false, nullable = true)
    @NotBlank
    private String genre;
    @Range(min = 1950, max = 2050)
    private Integer yearOfRelease;

    public Game(String name, String genre, Integer yearOfRelease) {
        this.name = name;
        this.genre = genre;
        this.yearOfRelease = yearOfRelease;
    }

    // REQUIRED TO CREATE A DEFAULT CONSTRUCTOR
    public Game() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                '}';
    }
}