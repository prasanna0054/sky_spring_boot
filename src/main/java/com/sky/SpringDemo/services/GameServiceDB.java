package com.sky.SpringDemo.services;


import com.sky.SpringDemo.domain.Game;

import com.sky.SpringDemo.repos.GameRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class GameServiceDB implements GameService {
    private GameRepo repo;

    public GameServiceDB(GameRepo repo) {
        this.repo = repo;
    }

    @Override
    public Game create(Game game) {
        return this.repo.save(game);
    }

    @Override
    public List<Game> create(List<Game> newGames) {
        return this.repo.saveAll(newGames);
    }

    @Override
    public Game get(int id) {
        return null;
    }

    @Override
    public List<Game> getAll() {
        return this.repo.findAll();
    }

    @Override
    public Game getById(int id) {
        Optional<Game> optionalGame = this.repo.findById(id);
        Game actualGame = optionalGame.get();

        return actualGame;
    }

    @Override
    public Game update(int id, String name, String genre, Integer yearOfRelease) {
        Game toUpdate = this.getById(id);

        if (name != null) toUpdate.setName(name);
        if (genre != null) toUpdate.setGenre(genre);
        if (yearOfRelease != null) toUpdate.setYearOfRelease(yearOfRelease);

        return this.repo.save(toUpdate);
    }

    @Override
    public Game remove(int id) {
        Game toDelete = this.getById(id);
        this.repo.deleteById(id);
        return toDelete;
    }

    @Override
    public List<Game> findByName(String name) {
        return this.repo.findByNameContainsIgnoreCase(name);
    }

    @Override
    public String findGenreByName(String name) {
        return this.repo.findGenreByName(name);
    }
}
