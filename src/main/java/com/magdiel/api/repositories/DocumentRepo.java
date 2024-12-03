package com.magdiel.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.magdiel.api.models.Document;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Long>{
    List<Document> findByProspectId(Long prospectId);
}
