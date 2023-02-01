package com.notethonker.notethonker.common;

import java.time.LocalDateTime;

import com.notethonker.notethonker.domain.Note;

public class NoteConstants {
    public static final Note NOTE = new Note("title", "content",null, null);
    public static final Note INVALID_NOTE = new Note("", "",null, null);

}
