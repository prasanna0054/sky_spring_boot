package com.sky.SpringDemo.services;

import com.sky.SpringDemo.domain.GameProvider;
import com.sky.SpringDemo.repos.GameProviderRepo;
import com.sky.SpringDemo.repos.GameRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameProviderService {
    private GameProviderRepo repo;

    public GameProviderService(GameProviderRepo repo) {
        this.repo = repo;
    }

    public GameProvider create(GameProvider gameProvider) {
        return this.repo.save(gameProvider);
    }

    public List<GameProvider> create(List<GameProvider> newGameProviders) {
        return this.repo.saveAll(newGameProviders);
    }


    public List<GameProvider> getAll() {
        return this.repo.findAll();
    }


    public GameProvider getById(int id) {
        GameProvider actualGameProvider = this.repo.findById(id).get();

        return actualGameProvider;
    }


    public GameProvider update(int id, String name, String address) {
        GameProvider toUpdate = this.getById(id);

        if (name != null) toUpdate.setName(name);
        if (address != null) toUpdate.setAddress(address);

        return this.repo.save(toUpdate);
    }


    public GameProvider remove(int id) {
        GameProvider toDelete = this.getById(id);
        this.repo.deleteById(id);
        return toDelete;
    }

}
