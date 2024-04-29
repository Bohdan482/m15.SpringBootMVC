package com.example.module15.SpringBootMVCProject.Notes;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class NoteService implements NoteCRUDService{
    private final List<Note> notes = new ArrayList<>();

    @Override
    public List<Note> listAll() {
        return notes;
    }

    @Override
    public Note add(Note note) {
        Random random = new Random();
        long id = random.nextLong(999);
        note.setId(id);
        notes.add(note);
        return note;
    }

    @Override
    public void deleteById(long id) {
        boolean isRemoved = notes.removeIf(note -> note.getId() == id);
        try {
            if (!isRemoved) {
                throw new NoteNotFoundException("Note with id " + id + " not found");
            }
        } catch (NoteNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Note note){
        boolean found = false;
        List<Note> copiedNotes = List.copyOf(notes);
        for (Note listNote : copiedNotes) {
            if (note.getId() == listNote.getId()) {
                notes.remove(listNote);
                notes.add(note);
                found = true;
            }
        }
        try {
            if(!found){
                throw new NoteNotFoundException("Note not found");
            }
        } catch (NoteNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public Note getById(long id) {
        Note resultNote = new Note();
        int index = -1;
        for (Note note : notes) {
            if (note.getId() == id) {
                resultNote.setId(note.getId());
                resultNote.setTitle(note.getTitle());
                resultNote.setContent(note.getContent());
                index = notes.indexOf(note);
            }
        }
        try {
            if (index == -1) {
                throw new NoteNotFoundException("Note not found");
            }
        } catch (NoteNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return resultNote;
    }
}