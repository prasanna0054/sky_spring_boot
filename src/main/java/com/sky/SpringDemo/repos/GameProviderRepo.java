package com.sky.SpringDemo.repos;

import com.sky.SpringDemo.domain.GameProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GameProviderRepo extends JpaRepository<GameProvider, Integer> {
}
