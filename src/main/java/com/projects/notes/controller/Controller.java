package com.projects.notes.controller;

import com.projects.notes.dto.EntryDTO;
import com.projects.notes.entity.Entry;
import com.projects.notes.service.service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/notes")
public class Controller {

    @Autowired
    public service service;

    @GetMapping()

    public List<EntryDTO>  getAll(){

      return   service.getAll();
    }

    @PostMapping()
    public EntryDTO create(@Valid @RequestBody EntryDTO dto)
    {
       return service.create(dto);
    }

    @GetMapping("/{id}")
    public EntryDTO findById(@PathVariable Long id)
    {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return "Deleted successfully";
    }

    @PutMapping("/{id}")
    public EntryDTO update(@PathVariable Long id,@Valid @RequestBody EntryDTO dto) {
        return service.updateById(id, dto);
    }




}
