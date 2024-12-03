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
@Table(name = "prospects")
public class Prospect implements Serializable{
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull(message = "Name is required.")
    @Size(max = 100, message = "Name cannot be longer than 100 characters")
    public String name;
    
    @NotNull(message = "Last Name is required.")
    @Size(max = 100, message = "Password cannot be longer than 100 characters")
    public String lastName;

    @Size(max = 100, message = "Second Last Name cannot be longer than 100 characters")
    public String secondLastName;

    @NotNull(message = "Phone is required.")
    @Size(max = 15, message = "Phone cannot be longer than 15 characters")
    public String phone;

    @NotNull(message = "Status is required.")
    public int status;
}
