package com.notethonker.notethonker.web;

import static com.notethonker.notethonker.common.NoteConstants.INVALID_NOTE;
import static com.notethonker.notethonker.common.NoteConstants.NOTE;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException.UnprocessableEntity;

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
    public void createNote_WithValidData_ReturnsCreated() throws Exception{
        when(noteService.create(NOTE)).thenReturn(NOTE); 
        mockMvc.perform(post("/notes").content(objectMapper.writeValueAsString(NOTE)).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$").value(NOTE));
    }

    @Test
    public void createNote_WithInvalidData_ReturnsBadRequest() throws Exception { 
        when(noteService.create(INVALID_NOTE)).thenThrow(UnprocessableEntity.class);
        // doThrow(MethodArgumentNotValidException.class).when(noteService.create(INVALID_NOTE));

        mockMvc.perform(post("/notes").content(objectMapper.writeValueAsString(INVALID_NOTE)).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void getNote_WithExistingId_ReturnsOK() throws Exception { 
        when(noteService.get(1L)).thenReturn(Optional.of(NOTE));
        
        mockMvc.perform(get("/notes/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(NOTE));
    }

    @Test
    public void getNote_WithNonExistingId_ReturnsNotFound() throws Exception { 
        when(noteService.get(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/notes/999"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void removeNote_WithExistingId_ReturnsNoContent() throws Exception { 
        mockMvc.perform(delete("/notes/1"))
        .andExpect(status().isNoContent());
    }

    @Test
    public void removeNote_WithNonExistingId_ReturnsBadRequest() throws Exception { 
        doThrow(EmptyResultDataAccessException.class).when(noteService).remove(999L);

        mockMvc.perform(delete("/notes/999"))
        .andExpect(status().isBadRequest());
    }
}
