package com.plainid.assignment.controller;

import com.plainid.assignment.Handlers.TrainerHandler;
import com.plainid.assignment.dao.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;


@RestController
@RequestMapping("/trainer")
public class TrainerController {

    private

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Get all pokemons in the world
     *
     * @return List of pokemons in the world.
     */
    @GetMapping("/{trainer_name}")
    public Trainer getTrainer(@PathVariable String trainer_name) {
        TrainerHandler dataHandler = new TrainerHandler(jdbcTemplate);

        return   dataHandler.getTrainer(trainer_name);

    }


    @GetMapping("/{trainer_name}/catch/{pokemon_name}")
    public HashMap<String, ArrayList<String>> getTrainerAndCatch(@PathVariable String trainer_name, @PathVariable String pokemon_name) {
        TrainerHandler dataHandler = new TrainerHandler(jdbcTemplate);
        return dataHandler.getTrainerAndCatch(trainer_name, pokemon_name);
    }









}