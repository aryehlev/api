package com.plainid.assignment;

import com.plainid.assignment.dao.PokemonList;
import com.plainid.assignment.dao.Trainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TrainerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void tesGetTrainerData(){
        Trainer trainer = restTemplate.getForEntity("http://localhost:" + port + "/trainer/trainer1",
                Trainer.class).getBody();
        assertThat(trainer).isNotNull();
        assertThat(trainer.getLevel()).isEqualTo(2);
        assertThat(trainer.getName()).isEqualTo("trainer1");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Chikorita");
        expected.add("Victreebel");
        expected.add("Bulbasaur");

        assertThat(trainer.getBag()).isEqualTo(expected);

    }


    @Test
    public void testCatchTrainerPokemon(){
        HashMap result = restTemplate.getForEntity("http://localhost:" + port + "/trainer/trainer1/catch/Vulpix",
                HashMap.class).getBody();
        assertThat(result).isNotNull();
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Chikorita");
        expected.add("Victreebel");
        expected.add("Vulpix");

        assertThat(result.get("bag")).isEqualTo(expected);


    }
}
