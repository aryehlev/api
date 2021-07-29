package com.plainid.assignment;

import com.plainid.assignment.dao.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class PokemonTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllPokemons(){
        PokemonList pokemonList = restTemplate.getForEntity("http://localhost:" + port + "/pokemon/list",
                PokemonList.class).getBody();
        assertThat(pokemonList).isNotNull();
        assertThat(pokemonList.getPokemons()).isNotNull();

    }

    @Test
    public void testBattle(){
        BattleResponse response = restTemplate.getForEntity("http://localhost:" + port + "/battle/trainer1/trainer2",
                BattleResponse.class).getBody();
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualByComparingTo(StatusType.Success);
        assertThat(response.getMessage()).isEqualTo("trainer1 wins");
    }

    @Test
    public void testGetTrainers(){
        TrainerList trainers = restTemplate.getForEntity("http://localhost:" + port + "/trainers",
                TrainerList.class).getBody();
        assertThat(trainers).isNotNull();
        Trainer first = trainers.getTrainers().get(0);
        assertThat(first.getLevel()).isEqualTo(1);
        assertThat(first.getName()).isEqualTo("trainer2");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Vaporeon");
        expected.add("Kingler");
        expected.add("Lapras");

        assertThat(first.getBag()).isEqualTo(expected);

    }

    @Test
    public void testCatchTrainerPokemon(){
        HashMap result = restTemplate.getForEntity("http://localhost:" + port + "/trainer/trainer1/catch/Vulpix",
                HashMap.class).getBody();
        assertThat(result).isNotNull();
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Bulbasaur");
        expected.add("Victreebel");
        expected.add("Vulpix");

        assertThat(result.get("bag")).isEqualTo(expected);


    }

    @Test
    public void testGetTrainerData(){
        Trainer trainer = restTemplate.getForEntity("http://localhost:" + port + "/trainer/trainer1",
                Trainer.class).getBody();


        assertThat(trainer).isNotNull();
        assertThat(trainer.getLevel()).isEqualTo(2);
        assertThat(trainer.getName()).isEqualTo("trainer1");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Bulbasaur");

        expected.add("Victreebel");
        expected.add("Chikorita");

        assertThat(trainer.getBag()).isEqualTo(expected);

    }
}
