package com.plainid.assignment.converter.mapper;

import com.plainid.assignment.Handlers.PokemonHandler;
import com.plainid.assignment.dao.Trainer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerRowMapper implements RowMapper<Trainer> {
    PokemonHandler dh;

    public TrainerRowMapper(PokemonHandler dh) {
        this.dh = dh;
    }

    @Override
    public Trainer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Trainer trainer = new Trainer();

        trainer.setName(rs.getString("NAME"));
        trainer.setPokemons();
        trainer.setFirstPokemon(dh.GetPokemonByID(rs.getInt("FIRST_POKEMON")));
        trainer.setLevel(rs.getInt("LEVEL"));
        trainer.setSecondPokemon(dh.GetPokemonByID(rs.getInt("SECOND_POKEMON")));
        trainer.setThirdPokemon(dh.GetPokemonByID(rs.getInt("THIRD_POKEMON")));
        trainer.setBag();
        return trainer;

    }


}
