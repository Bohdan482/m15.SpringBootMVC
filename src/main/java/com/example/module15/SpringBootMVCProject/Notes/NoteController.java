package com.example.module15.SpringBootMVCProject.Notes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService service;

    @GetMapping("/list")
    public ModelAndView getNotesList(){
        ModelAndView modelAndView = new ModelAndView("list of notes");
        modelAndView.addObject("noteList", service.listAll());
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteNotePost(@RequestParam("id") long id){
        service.deleteById(id);
        return getNotesList();
    }

    @GetMapping("/edit/id={id}")
    public ModelAndView getEditingForm(@PathVariable("id") long id){
        Note note = service.getById(id);
        ModelAndView model = new ModelAndView("edit form");
        model.addObject("oldTitle", note.getTitle());
       model.addObject("oldContent", note.getContent());
       model.addObject("id", id);
       return model;
    }

    @PostMapping("/edit")
    public ModelAndView saveNoteAndShowAll(@RequestParam(value = "id") long id,
                                           @RequestParam(value = "title") String title,
                                           @RequestParam(value = "content") String content
    ){
        Note note = new Note();
        note.setId(id);
        note.setTitle(title);
        note.setContent(content);
        service.update(note);
        return getNotesList();
    }
}