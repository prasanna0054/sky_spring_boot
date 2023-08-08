package com.sky.SpringDemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.SpringDemo.domain.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // loads the application context
@AutoConfigureMockMvc // create a MockMVC bean
@Sql(scripts = {"classpath:game-schema.sql", "classpath:game-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
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
        Game cricket = new Game("NFS", "Racing", 2008);
        System.out.println("DATA: " + cricket);
        String cricketJSON = this.mapper.writeValueAsString(cricket);
        System.out.println("JSON: " + cricketJSON);
        RequestBuilder req = post("/game/create").content(cricketJSON).contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkStatus = status().isCreated();
        Game responseBody = new Game(2, "NFS", "Racing", 2008);
        System.out.println("DATA: " + responseBody);
        String responseBodyJSON = this.mapper.writeValueAsString(responseBody);
        System.out.println("JSON: " + responseBodyJSON);
        ResultMatcher checkBody = content().json(responseBodyJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testCreate2() throws Exception {
        String cricketJSON = this.mapper.writeValueAsString(new Game("NFS", "Racing", 2008));
        String responseBodyJSON = this.mapper.writeValueAsString(new Game(2, "NFS", "Racing", 2008));
        this.mvc.perform(

                        post("/game/create")
                                .content(cricketJSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(responseBodyJSON));
    }

    @Test
    void testRead() throws Exception {
        final int id = 1;
        String responseBody = this.mapper.writeValueAsString(new Game(id, "Cricket", "Sports", 2007));
        this.mvc.perform(get("/game/get/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    void testUpdate() throws Exception {
        final Integer id = 1;
        final String name = "Cricket";

        String responseBody = this.mapper.writeValueAsString(new Game(id, "Cricket", "Sports", 2007));
        this.mvc.perform(patch("/game/update/" + id
                        + "?name=" + name
                ))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }


    @Test
    void testDelelte() throws Exception {
        final Integer id = 1;
        String responseBody = this.mapper.writeValueAsString(new Game(id, "Cricket", "Sports", 2007));
        this.mvc.perform(delete("/game/remove/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }


    @Test
    void testFindByName() throws Exception {
        final String name = "Cricket";

        String responseBody = this.mapper.writeValueAsString(List.of
                ( new Game(1,"Cricket", "Sports", 2007)));
        this.mvc.perform(get("/game/findByName/" + name))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    void testGetMultiple() throws Exception {


        String responseBody = this.mapper.writeValueAsString(List.of
                (new Game(1, "Cricket", "Sports", 2007)));
        this.mvc.perform(get("/game/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));


    }
}
