package com.notethonker.notethonker.web;

import static com.notethonker.notethonker.common.NoteConstants.NOTE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notethonker.notethonker.controllers.NoteController;
import com.notethonker.notethonker.service.NoteService;

@WebMvcTest(controllers = NoteController.class)
public class NoteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NoteService noteService;

    @Test
    public void createNote_WithValidData_ReturnsCreated()   throws Exception{
        when(noteService.create(NOTE)).thenReturn(NOTE); 
        mockMvc.perform(post("/notes").content(objectMapper.writeValueAsString(NOTE)).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$").value(NOTE));
    }

}
