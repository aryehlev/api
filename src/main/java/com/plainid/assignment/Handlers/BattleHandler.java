package com.plainid.assignment.Handlers;

import com.plainid.assignment.converter.mapper.TrainerRowMapper;
import com.plainid.assignment.dao.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BattleHandler extends PokemonHandler {
    public BattleHandler(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }


    public BattleResponse battle(String trainer1, String trainer2){
        List<Trainer> rows = jdbcTemplate.query("SELECT * from TRAINER WHERE Name = ? OR Name = ?",new TrainerRowMapper(this), trainer1, trainer2);


        if (rows.size() != 2) throw new IllegalArgumentException();

        Trainer firstTrainer = rows.get(0);
        Trainer secondTrainer = rows.get(1);

        if (firstTrainer.NumOfPokemons() != 3  || secondTrainer.NumOfPokemons() !=  3) return new BattleResponse(StatusType.Error, "canceled");

        int numOfWins1 = 0;
        int numOfWins2 = 0;
        Pokemon[] firstPokemons = firstTrainer.Pokemons();
        Pokemon[] secondPokemons = secondTrainer.Pokemons();

        for (int i = 0; i < 3; i++){
            PokemonType type1 = firstPokemons[i].getType();
            PokemonType type2 = secondPokemons[i].getType();

            switch (type1){
                case Fire:
                    switch (type2) {
                        case Grass:
                            numOfWins1++;
                            break;
                        case Water:
                            numOfWins2++;
                        default:
                            break;
                    }
                    break;
                case Grass:
                    switch (type2) {
                        case Water:
                            numOfWins1++;
                            break;
                        case Fire:
                            numOfWins2++;
                        default:
                            break;
                    }
                    break;
                case Water:
                    switch (type2) {
                        case Fire:
                            numOfWins1++;
                            break;
                        case Grass:
                            numOfWins2++;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }


        if(numOfWins1 == numOfWins2) {
            jdbcTemplate.execute(String.format("UPDATE trainer SET  LEVEL = '%d' WHERE  name = '%s'", firstTrainer.getLevel() + 1, firstTrainer.getName()));
            jdbcTemplate.execute(String.format("UPDATE trainer SET  LEVEL = '%d' WHERE  name = '%s'", secondTrainer.getLevel() + 1, secondTrainer.getName()));
            return new BattleResponse(StatusType.Draw, "draw");
        }

        if (numOfWins1 > numOfWins2){
            jdbcTemplate.execute(String.format("UPDATE trainer SET  LEVEL = '%d' WHERE  name = '%s'", firstTrainer.getLevel() + 2, firstTrainer.getName()));

            return new BattleResponse(StatusType.Success, String.format("%s wins", firstTrainer.getName()));
        }
        jdbcTemplate.execute(String.format("UPDATE trainer SET  LEVEL = '%d' WHERE  name = '%s'", secondTrainer.getLevel() + 2, secondTrainer.getName()));

        return new BattleResponse(StatusType.Success, String.format("%s wins", secondTrainer.getName()));

    }
}
