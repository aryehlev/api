package com.plainid.assignment.Handlers;

import com.plainid.assignment.converter.mapper.PokemonRowMapper;
import com.plainid.assignment.dao.Pokemon;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PokemonHandler{
    JdbcTemplate jdbcTemplate;


    public PokemonHandler(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Pokemon GetPokemonByName(String name){
        if (name == null){
            return null;
        }
        List<Pokemon> rows = jdbcTemplate.query("SELECT * from POKEMON WHERE name= ? ", new PokemonRowMapper(), name);

        try {
            return rows.get(0);
        }catch (Exception IndexOutOfBoundsException){
            return null;
        }
    }

    public Pokemon GetPokemonByID(int id){
        if (id == 0){
            return null;
        }
        List<Pokemon> rows = jdbcTemplate.query("SELECT * from POKEMON WHERE id = ?", new PokemonRowMapper(), id);


        try {
            return rows.get(0);
        }
        catch (Exception IndexOutOfBoundsException){
            return null;
        }

    }
}
