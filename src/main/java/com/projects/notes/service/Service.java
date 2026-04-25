package com.projects.notes.service;

import com.projects.notes.dto.EntryDTO;
import com.projects.notes.entity.Entry;
import com.projects.notes.exception.ResourceNotFoundException;
import com.projects.notes.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@org.springframework.stereotype.Service

public class Service {

    public Repo repo;
    public Service(Repo repo)
    {
        this.repo= repo;
    }



    public List<EntryDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public EntryDTO create(EntryDTO dto)
    {
        Entry entry= convertToEntity(dto);
        Entry saved= repo.save(entry);


       return convertToDTO(saved);
    }
    public EntryDTO getById(Long id) {
          Entry entry = repo.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Entry not found with id " + id));
          return convertToDTO(entry);
    }

    public void deleteById (Long id)
    {
        Entry entry = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found with id:"+id));

        repo.delete(entry);

    }
    public EntryDTO updateById(Long id, EntryDTO dto) {
        Entry existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found with id " + id));

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());

       Entry updated= repo.save(existing);
        return convertToDTO(updated);
    }

    public EntryDTO convertToDTO(Entry entry) {
        EntryDTO dto = new EntryDTO();
        dto.setId(entry.getId());
        dto.setTitle(entry.getTitle());
        dto.setDescription(entry.getDescription());
        return dto;
    }

    public Entry convertToEntity(EntryDTO dto) {
        Entry entry = new Entry();
        entry.setTitle(dto.getTitle());
        entry.setDescription(dto.getDescription());
        return entry;
    }


}
