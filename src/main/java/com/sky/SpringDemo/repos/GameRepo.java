package com.sky.SpringDemo.repos;
import com.sky.SpringDemo.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GameRepo extends JpaRepository<Game, Integer> {
    List<Game> findByNameContainsIgnoreCase(String name);

    // JPQL -> Java Persistence Query Language
    @Query(value = "SELECT genre FROM game WHERE name = ?1", nativeQuery = true)
    String findGenreByName(String name);

    Object deleteById(Game toRemove);
}

