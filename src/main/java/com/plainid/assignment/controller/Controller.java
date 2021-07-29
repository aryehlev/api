package com.plainid.assignment.controller;

import com.plainid.assignment.Handlers.TrainerHandler;
import com.plainid.assignment.dao.TrainerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {

    private

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Get all pokemons in the world
     * @return List of pokemons in the world.
     */
    @GetMapping("/trainers")
    public TrainerList getTrainers() {
        TrainerHandler dh = new TrainerHandler(jdbcTemplate);

        return dh.getTrainers();
    }
}
