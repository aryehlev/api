package com.plainid.assignment;

import com.plainid.assignment.dao.BattleResponse;
import com.plainid.assignment.dao.PokemonList;
import com.plainid.assignment.dao.StatusType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class BattleTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    public void testBattle(){
        BattleResponse response = restTemplate.getForEntity("http://localhost:" + port + "/battle/trainer1/trainer2",
                BattleResponse.class).getBody();
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualByComparingTo(StatusType.Success);
        assertThat(response.getMessage()).isEqualTo("trainer1 wins");
    }
}
