package com.learn.crm.repository;

import com.learn.crm.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<Project,Long> {
    boolean existsByName(String name);
}
