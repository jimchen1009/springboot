package com.ximuyi.demo.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Random;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    private static  Random random = new Random();

    @Autowired
    private MockMvc mvc;

    @Test
    public void postAccount() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/account")
                            .param("name", "Jim")
                            .param("money", "1.0")
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void pushAccount() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/account/4")
                            .param("name","Jim" + random.nextInt(100))
                            .param("money", String.valueOf(random.nextDouble()))
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void accountList() throws Exception {
            mvc.perform(MockMvcRequestBuilders.get("/account/list")
                                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andDo(print());
    }
}
