package com.example.module15.SpringBootMVCProject.Notes;

public class NoteNotFoundException extends Exception{
    public NoteNotFoundException(String errorMassage){
        super(errorMassage);
    }
}
