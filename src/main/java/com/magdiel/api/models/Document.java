package com.magdiel.api.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "documents")
public class Document implements Serializable{
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull(message = "Prospect ID is required.")
    public Long prospectId;

    @NotNull(message = "Name is required.")
    @Size(max = 100, message = "Name cannot be longer than 100 characters")
    public String name;
    
    @NotNull(message = "Document is required.")
    public byte[] document;

    public Document(Long prospectId, String name, byte[] document) {
        this.prospectId = prospectId;
        this.name = name;
        this.document = document;
    }

    public Document() { }
}
