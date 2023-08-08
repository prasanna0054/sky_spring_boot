package com.sky.SpringDemo.rest;

import com.sky.SpringDemo.domain.GameProvider;
import com.sky.SpringDemo.services.GameProviderService;
import com.sky.SpringDemo.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gameprovider")
public class GameProviderController {

    private GameProviderService service;

    public GameProviderController(GameProviderService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World";
    }


    @PostMapping("/create")
    public ResponseEntity<GameProvider> create(@RequestBody GameProvider gameProvider) {
        return new ResponseEntity<>(this.service.create(gameProvider), HttpStatus.CREATED);
    }

    @PostMapping("/createMultiple")
    public ResponseEntity<List<GameProvider>> create(@RequestBody List<GameProvider> newGameProviders) {
        System.out.println("RECEIVED: " + newGameProviders);
        if (this.service.create(newGameProviders) != null) {
            return new ResponseEntity<>(newGameProviders, HttpStatus.CREATED);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/getAll")
    public List<GameProvider> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/get/{id}")
    public GameProvider getById(@PathVariable int id) {
        return this.service.getById(id);
    }

    // @RequestParam works like @PathParam but it allows you to make certain parameters mandatory
    @PatchMapping("/update/{id}")
    public GameProvider update(@PathVariable int id, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "address", required = false) String address) {
        return this.service.update(id, name, address);

    }

    @DeleteMapping("/remove/{id}")
    public GameProvider remove(@PathVariable int id) {
        return this.service.remove(id);
    }



}
