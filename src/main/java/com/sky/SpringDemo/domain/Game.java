package com.sky.SpringDemo.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
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
@ManyToOne(targetEntity = GameProvider.class)
private GameProvider gameProvider;

    public GameProvider getGameProvider() {
        return gameProvider;
    }

    public void setGameProvider(GameProvider gameProvider) {
        this.gameProvider = gameProvider;
    }

    public Game(String name, String genre, Integer yearOfRelease) {
        this.name = name;
        this.genre = genre;
        this.yearOfRelease = yearOfRelease;
    }

    // REQUIRED TO CREATE A DEFAULT CONSTRUCTOR
    public Game() {
    }

    public Game(Integer id) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Game(Integer id, String name, String genre, Integer yearOfRelease){
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.yearOfRelease = yearOfRelease;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (!id.equals(game.id)) return false;
        if (!name.equals(game.name)) return false;
        if (!genre.equals(game.genre)) return false;
        return yearOfRelease.equals(game.yearOfRelease);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + yearOfRelease.hashCode();
        return result;
    }
}