package com.notethonker.notethonker.domain;

import static com.notethonker.notethonker.common.NoteConstants.NOTE;
import static com.notethonker.notethonker.common.NoteConstants.FULL_NOTE;
import static com.notethonker.notethonker.common.NoteConstants.INVALID_NOTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.util.Optional;

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

    @Test
    public void createNote_WithInvalidData_ReturnsBadRequest(){ 
        when(noteRepo.save(INVALID_NOTE)).thenThrow(RuntimeException.class);


        assertThatThrownBy(()-> noteRepo.save(INVALID_NOTE)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getNote_WithExistingId_ReturnsNote() { 
        when(noteRepo.findById(FULL_NOTE.getId())).thenReturn(Optional.of(FULL_NOTE));

        Optional<Note> sut = noteService.get(FULL_NOTE.getId());

        assertThat(sut).isPresent();
        assertThat(sut.get().getId()).isEqualTo(FULL_NOTE.getId());
        assertThat(sut.get().getTitle()).isEqualTo(FULL_NOTE.getTitle());
        assertThat(sut.get().getContent()).isEqualTo(FULL_NOTE.getContent());
    }

    @Test
    public void getNote_WithNonExistingId_ReturnsEmpty() {
        when(noteRepo.findById(999L)).thenReturn(Optional.empty());

        Optional<Note> sut = noteService.get(999L);

        assertThat(sut).isEmpty();
    }

    @Test
    public void deleteNote_WithoutId_ThrowsExcetion() { 
        doThrow(IllegalArgumentException.class).when(noteRepo).deleteById(null);

        assertThatThrownBy(() -> noteService.remove(null)).isInstanceOf(IllegalArgumentException.class);
    }
}
