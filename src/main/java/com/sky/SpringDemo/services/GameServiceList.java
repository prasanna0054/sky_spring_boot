package com.sky.SpringDemo.services;


import com.sky.SpringDemo.domain.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceList implements GameService {

    private List<Game> games = new ArrayList<>();

    @Override
    public Game create(Game game) {
        this.games.add(game);
        return this.games.get(this.games.size() - 1);
    }

    @Override
    public List<Game> create(List<Game> newGames) {
        if (this.games.addAll(newGames)) {
            return newGames;
        } else {
            return null;
        }

    }

    @Override
    public Game get(int id) {
        return this.games.get(id);
    }

    @Override
    public List<Game> getAll() {
        return this.games;
    }

    @Override
    public Game getById(int id) {
        Game found = this.games.get(id);
        return found;
    }

    @Override
    public Game update(int id, String name, String genre, Integer yearOfRelease) {
        Game toUpdate = this.games.get(id);
        if (name != null) toUpdate.setName(name);
        if (genre != null) toUpdate.setGenre(genre);
        if (yearOfRelease != null) toUpdate.setYearOfRelease(yearOfRelease);
        return toUpdate;
    }

    @Override
    public Game remove(int id) {

        return this.games.remove(id);
    }

    @Override
    public List<Game> findByName(String name) {
        List<Game> found = new ArrayList<>();
        for (Game g : this.games) {
            if (name.equals(g.getName())) found.add(g);
        }
        return found;
    }

    @Override
    public String findGenreByName(String name) {
        return null;
    }

}