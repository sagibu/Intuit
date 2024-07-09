package com.intuit.player.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.intuit.player.Player;

@Repository
public interface PostgresDB extends JpaRepository<Player, String> {
}
