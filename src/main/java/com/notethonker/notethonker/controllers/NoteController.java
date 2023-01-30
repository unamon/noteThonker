package com.notethonker.notethonker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notethonker.notethonker.domain.Note;
import com.notethonker.notethonker.service.NoteService;

@RestController
@RequestMapping("/api/notes/")
public class NoteController {
   
    @Autowired
    NoteService noteService;

    @PostMapping
    public ResponseEntity<Note> create(@RequestBody Note newNote) {
        Note note = noteService.create(newNote);
        return ResponseEntity.status(HttpStatus.CREATED).body(note);
    }
}
