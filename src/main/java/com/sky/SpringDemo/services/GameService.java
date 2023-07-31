package com.sky.SpringDemo.services;
import com.sky.SpringDemo.domain.Game;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GameService {

    Game create(Game game);
    List<Game> create(List<Game> newGames);

    Game get(int id);

    List<Game> getAll();

    Game update(int id, String name, String genre, Integer yearOfRelease);

    Game remove(int id);
}