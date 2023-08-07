package com.sky.SpringDemo.service;

import com.sky.SpringDemo.domain.Game;
import com.sky.SpringDemo.repos.GameRepo;
import com.sky.SpringDemo.services.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class GameServiceDBUnitTest {

    @Autowired
    private GameService service;
    @MockBean
    private GameRepo repo;

    @Test
    void testUpdate(){
        int id =1;
        Game existing = new Game(id, "NFS", "Racing", 2007);
        String name ="NFS Hot Pursuit";
        String genre ="Arcade";
        Integer yearOfRelease = 2009;

        Game updated = new Game(id, name, genre, yearOfRelease);

        Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(existing));
        Mockito.when(this.repo.save(updated)).thenReturn(updated);

        Assertions.assertEquals(updated,this.service.update(id, name, genre, yearOfRelease));

        Mockito.verify(this.repo, Mockito.times(1)).findById(id);
        Mockito.verify(this.repo, Mockito.times(1)).save(updated);


    }

    @Test
    void testRemove(){
        Integer id =1;
        Game existing = new Game(id, "NFS", "Racing", 2007);
//        Game toRemove = new Game(id);
        Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(existing));
        Assertions.assertEquals(existing,this.service.remove(id));
    }

    @Test
    void testGetAll(){
        List <Game> gameList = new ArrayList<>(List.of(new Game(1, "NFS", "Racing", 2007)));

        Mockito.when(this.repo.findAll()).thenReturn(gameList);
        Assertions.assertEquals(gameList, this.service.getAll());

    }

    @Test
    void testCreate(){

        Game newGame = new Game("NFS", "Racing", 2007);

        Mockito.when(this.repo.save(newGame)).thenReturn(newGame);
        Assertions.assertEquals(newGame, this.service.create(newGame));

    }

}
