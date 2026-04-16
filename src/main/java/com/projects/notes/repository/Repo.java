package com.projects.notes.repository;

import com.projects.notes.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<Entry,Long> {
}
