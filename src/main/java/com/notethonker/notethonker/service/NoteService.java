package com.notethonker.notethonker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

    public List<Note> list(){
        return noteRepo.findAll();
    }

    public Optional<Note> get(Long id){
        return noteRepo.findById(id);
    }

    public void remove(Long id){
        noteRepo.deleteById(id);
    }

    public List<Note> find(String searchString) { 
       return noteRepo.search(searchString);
    }
    public Optional<Note> update(Long id, Note updateNote) {
        Optional<Note> noteDB = noteRepo.findById(id);
        if(noteDB.isEmpty()) {
            return noteDB;
        }
        updateNote.setId(id);
        return Optional.of(noteRepo.save(updateNote));
    }
}
