package com.notethonker.notethonker.domain;
import static com.notethonker.notethonker.common.NoteConstants.EMPTY_NOTE;
import static com.notethonker.notethonker.common.NoteConstants.INVALID_NOTE;
import static com.notethonker.notethonker.common.NoteConstants.NOTE;
// import static org.assertj.core.api.Assertions.hasSize;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;

import com.notethonker.notethonker.repository.NoteRepository;

@DataJpaTest
public class NoteRepositoryTest {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    TestEntityManager testEntityManager;

    @AfterEach
    public void afterEach(){
        NOTE.setId(null);
    }
    
    @Test
    public void createNote_WithValidData_ReturnsNote() { 
        Note note = noteRepository.save(NOTE);
        Note sut = testEntityManager.find(Note.class, note.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.getTitle()).isEqualTo(NOTE.getTitle());
        assertThat(sut.getContent()).isEqualTo(NOTE.getContent());
    }

    @Test
    public void createNote_WithInvalidData_ThrowsException() { 
        assertThatThrownBy(()-> noteRepository.save(EMPTY_NOTE)).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(()-> noteRepository.save(INVALID_NOTE)).isInstanceOf(RuntimeException.class);

    }

    @Test
    public void getNote_ByExistingId_ReturnsNote() { 
        Note note = testEntityManager.persistFlushFind(NOTE);
        testEntityManager.detach(note);

        Optional<Note> sut = noteRepository.findById(note.getId());

        assertThat(sut).isPresent();
        assertThat(sut.get()).isEqualTo(note);
    }

    @Test
    public void getNote_ByNonExistingId_ReturnsEmpty() { 
        Optional<Note> sut = noteRepository.findById(999L);

        assertThat(sut).isEmpty();
    }

    @Sql(scripts = "/import.sql")
    @Test
    public void removeNote_ByExistingId_ReturnsEmpty() {
        noteRepository.deleteById(1l);
        List<Note> sut = noteRepository.findAll();

        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(2);
    }

    @Test
    public void removeNot_ByNonExistingId_ThrowsException() {
        assertThatThrownBy(() -> noteRepository.deleteById(1L)).isInstanceOf(EmptyResultDataAccessException.class);
    }
}
