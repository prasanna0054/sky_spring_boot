package com.sky.SpringDemo.rest;


import com.sky.SpringDemo.domain.Game;
import com.sky.SpringDemo.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    public GameController() {
    }

    @GetMapping("/get/{id}")
    public Game getById(@PathVariable int id) {
        return this.service.getById(id);
    }

    @GetMapping("/getAll")
    public List<Game> getAll() {
        return this.service.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Game> create(@RequestBody Game game) {
        return new ResponseEntity<>(this.service.create(game), HttpStatus.CREATED);
    }

    // Behind the scenes, using Jackson library to convert JSON into whatever parameter type you would like
    // This works as long as the parameters you pass in match
    @PostMapping("/createMultiple")
    public ResponseEntity<List<Game>> create(@RequestBody List<Game> newGames) {
        System.out.println("RECEIVED: " + newGames);
        if (this.service.create(newGames) != null) {
            return new ResponseEntity<>(newGames, HttpStatus.CREATED);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/update/{id}")
    public Game update(@PathVariable Integer id,
                       @RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "genre", required = false) String genre,
                       @RequestParam(value = "yearOfRelease", required = false) Integer yearOfRelease) {
        return this.service.update(id, name, genre, yearOfRelease);
    }

    @DeleteMapping("/remove/{id}")
    public Game remove(@PathVariable Integer id) {
        return this.service.remove(id);
    }

    @GetMapping("/findByName/{name}")
    public List<Game> findByName(@PathVariable String name) {
        return this.service.findByName(name);
    }

    @GetMapping("/findGenreByName/{name}")
    public String findGenreByName(@PathVariable String name) {
        return this.service.findGenreByName(name);
    }
}