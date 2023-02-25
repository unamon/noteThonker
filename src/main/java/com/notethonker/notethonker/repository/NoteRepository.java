package com.notethonker.notethonker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.notethonker.notethonker.domain.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{
    @Query(value="SELECT * from notes n WHERE n.title LIKE %:searchString% OR n.content LIKE %:searchString%", nativeQuery=true)
    List<Note> search(@Param("searchString") String searchString);
    
    List<Note> findByTitleContaining(String searchString);
}
