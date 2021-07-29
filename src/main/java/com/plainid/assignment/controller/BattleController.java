package com.plainid.assignment.BattleController;

import com.plainid.assignment.Handlers.BattleHandler;
import com.plainid.assignment.dao.BattleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/battle")
public class BattleController {

    private

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Get all pokemons in the world
     * @return List of pokemons in the world.
     */
    @GetMapping("/{trainer1}/{trainer2}")
    public BattleResponse getTrainers(@PathVariable String trainer1, @PathVariable String trainer2) {
        BattleHandler dh = new BattleHandler(jdbcTemplate);
        return dh.battle(trainer1, trainer2);
    }
}