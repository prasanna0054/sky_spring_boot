package com.sky.SpringDemo.rest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.SpringDemo.domain.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // loads the application context
@AutoConfigureMockMvc // create a MockMVC bean
public class GameMVCTest {
    @Autowired // tells spring to inject the MockMVC bean into this class
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;


    @Test
    void testCreate() throws Exception {
        // METHOD: POST
        // PATH: /game/create
        // BODY: JSON
        // HEADERS: CONTENT-TYPE: application\json
        Game cricket = new Game( "Cricket", "Sports", 2007);
        System.out.println("DATA: " + cricket);
        String cricketJSON = this.mapper.writeValueAsString(cricket);
        System.out.println("JSON: " + cricketJSON);
        RequestBuilder req = MockMvcRequestBuilders.post("/game/create").content(cricketJSON).contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();
        Game responseBody = new Game("Cricket", "Sports", 2007);
        System.out.println("DATA: " + responseBody);
        String responseBodyJSON = this.mapper.writeValueAsString(responseBody);
        System.out.println("JSON: " + responseBodyJSON);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(responseBodyJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }
    @Test
    void testCreate2() throws Exception {
        String cricketJSON = this.mapper.writeValueAsString(new Game(1,"Cricket", "Sports", 2007));
        String responseBodyJSON = this.mapper.writeValueAsString(new Game(1,"Cricket", "Sports", 2007));
        this.mvc.perform(
                        MockMvcRequestBuilders.
                                post("/game/create")
                                .content(cricketJSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(responseBodyJSON));
    }
}
