package com.notethonker.notethonker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notethonker.notethonker.domain.Note;
import com.notethonker.notethonker.repository.NoteRepository;

@Service
public class NoteService {
    
    @Autowired
    NoteRepository noteRepo;
    
    public Note create(Note note){
        return noteRepo.save(note); 
    }
}
