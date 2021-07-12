package uk.co._4loop.jenkinsbuild.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {HelloController.class})
@WebMvcTest
class HelloControllerTest {

    public static final String PARAM_NAME = "name";

    @Autowired
    private MockMvc mockMvc;


    @Test
    void hello() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/hello")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> model = result.getModelAndView().getModel();

        assertFalse(model.isEmpty());
        assertTrue(model.containsKey(PARAM_NAME));
        assertEquals("World", model.get(PARAM_NAME));
    }

    @Test
    void hello_with_name_param() throws Exception {

        final String name = "anythingwilldo";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/hello?name="+name)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> model = result.getModelAndView().getModel();

        assertFalse(model.isEmpty());
        assertTrue(model.containsKey(PARAM_NAME));
        assertEquals(name, model.get(PARAM_NAME));
    }
}