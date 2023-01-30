package com.notethonker.notethonker.domain;

import static org.mockito.Mockito.when;
import static com.notethonker.notethonker.common.NoteConstants.NOTE;
import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.notethonker.notethonker.repository.NoteRepository;
import com.notethonker.notethonker.service.NoteService;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {
    @InjectMocks
    private NoteService noteService;
    @Mock
    private NoteRepository noteRepo;

    @Test
    public void createNote_WithValidData_ReturnsNote(){ 
        when(noteRepo.save(NOTE)).thenReturn(NOTE);

        Note sut = noteService.create(NOTE);

        assertThat(sut).isEqualTo(NOTE);
    }
}
