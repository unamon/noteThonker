package com.notethonker.notethonker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notethonker.notethonker.domain.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{
    
}
