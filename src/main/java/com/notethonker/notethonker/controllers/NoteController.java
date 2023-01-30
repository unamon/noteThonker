package com.notethonker.notethonker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notethonker.notethonker.domain.Note;
import com.notethonker.notethonker.service.NoteService;

@RestController
@RequestMapping("api/notes")
public class NoteController {
   
    @Autowired
    NoteService noteService;

    @PostMapping
    public ResponseEntity<Note> create(@RequestBody Note newNote) {
        Note note = noteService.create(newNote);
        return ResponseEntity.status(HttpStatus.CREATED).body(note);
    }

    @GetMapping
    public ResponseEntity<List<Note>> list() {
        List<Note> notes = noteService.list();
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> get(@PathVariable Long id) {
        return noteService.get(id).map(note -> ResponseEntity.ok(note))
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        noteService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@PathVariable Long id, @RequestBody Note note){
        return noteService.update(id, note).map(n -> ResponseEntity.ok(n))
        .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
