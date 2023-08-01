package com.sky.SpringDemo.services;

import com.sky.SpringDemo.domain.Game;

import java.util.List;

public interface GameService {

    Game create(Game game);

    List<Game> create(List<Game> newGames);

    Game get(int id);

    List<Game> getAll();

    Game getById(int id);

    Game update(int id, String name, String genre, Integer yearOfRelease);

    Game remove(int id);

    List<Game> findByName(String name);

    String findGenreByName(String name);
}
