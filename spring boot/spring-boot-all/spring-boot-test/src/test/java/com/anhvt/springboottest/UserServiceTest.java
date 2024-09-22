/**
 * Copyright 2024
 * Name: UserServiceTest
 */
package com.anhvt.springboottest;

import com.anhvt.springboottest.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/22/2024
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    private MockMvc mockMvc;
    private MockHttpSession session;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    ObjectMapper mapper;


    @Before
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
        User user =new User(1, "Anhvt");
        session.setAttribute("user",user);
    }

    @Test
    public void test() throws Exception {
        User user = new User(1, "Anhvt");

        String userJson = mapper.writeValueAsString(user);


//		mockMvc.perform(MockMvcRequestBuilders.post("/user/save").content(jsonStr.getBytes()));

        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.get("/users")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(userJson.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }
}
