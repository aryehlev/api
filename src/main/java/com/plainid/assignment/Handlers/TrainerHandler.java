package com.plainid.assignment.Handlers;

import com.plainid.assignment.converter.mapper.TrainerRowMapper;
import com.plainid.assignment.dao.Pokemon;
import com.plainid.assignment.dao.Trainer;
import com.plainid.assignment.dao.TrainerList;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrainerHandler extends PokemonHandler {
    public TrainerHandler(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public Trainer getTrainer(String trainer_name) {
        List<Trainer> rows = jdbcTemplate.query("SELECT * from trainer WHERE NAME = ? ",new TrainerRowMapper(this), trainer_name);


        return rows.get(0);
    }

    public HashMap<String, ArrayList<String>> getTrainerAndCatch(String trainer_name, String pokemon_name) {
        List<Trainer> rows = jdbcTemplate.query("SELECT * from trainer WHERE name = ?",  new TrainerRowMapper(this), trainer_name);

        Trainer trainer = rows.get(0);


        Pokemon newPokemon = this.GetPokemonByName(pokemon_name);
        int id = newPokemon.getId();


        int nextPokeman = trainer.NextPokemon();

        switch (nextPokeman){
            case 1:
                jdbcTemplate.execute(String.format("UPDATE trainer SET  first_pokemon = '%s' WHERE  name = '%s'", id, trainer_name));
                trainer.setFirstPokemon(newPokemon);
                break;
            case 2:
                jdbcTemplate.execute(String.format("UPDATE trainer SET  second_pokemon = '%s' WHERE  name = '%s'", id, trainer_name));
                trainer.setSecondPokemon(newPokemon);

                break;
            default:
                jdbcTemplate.execute(String.format("UPDATE trainer SET  third_pokemon = '%s' WHERE  name = '%s'", id, trainer_name));
                trainer.setThirdPokemon(newPokemon);

        }
        trainer.setBag();
        HashMap<String, ArrayList<String>> lazyResult = new HashMap<>();
        lazyResult.put("bag", trainer.getBag());
        return lazyResult;
    }

    public TrainerList getTrainers() {
        List<Trainer> rows = jdbcTemplate.query("SELECT * from TRAINER",new TrainerRowMapper(this));
        TrainerList trainerList = new TrainerList();
        trainerList.setTrainers(rows);
        trainerList.sortTrainers();

        return trainerList;
    }

}
