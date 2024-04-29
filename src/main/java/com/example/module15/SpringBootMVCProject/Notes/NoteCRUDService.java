package com.example.module15.SpringBootMVCProject.Notes;

import java.util.List;

public interface NoteCRUDService {
    List<Note> listAll();
    Note add(Note note);
    void deleteById(long id);
    void update(Note note) throws NoteNotFoundException;
    Note getById(long id);
}
