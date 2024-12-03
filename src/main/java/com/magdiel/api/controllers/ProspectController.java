package com.magdiel.api.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.magdiel.api.models.Document;
import com.magdiel.api.models.Prospect;
import com.magdiel.api.services.ProspectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/prospects")
public class ProspectController {
    @Autowired
    private ProspectService prospectService;

    @GetMapping
    public ResponseEntity<List<Prospect>> getAll(){
        List<Prospect> prospects = prospectService.findAll();
        return new ResponseEntity<>(prospects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        Prospect prospect = prospectService.find(id);
        return new ResponseEntity<>(prospect, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Prospect> add(@RequestBody @Valid Prospect prospect){
        Prospect newUser = prospectService.add(prospect);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Prospect> update(@RequestBody @Valid Prospect prospect){
        Prospect updatedUser = prospectService.update(prospect);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        prospectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMultiple(@RequestBody List<Long> ids){
        for (Long id : ids) {
            prospectService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadDocument(@RequestParam Long id,  @RequestParam String name, @RequestParam MultipartFile document) throws IOException {
        prospectService.uploadFile(id, name, document);
        return ResponseEntity.ok("File uploaded Successfully");
    }

    @GetMapping(
        value = "/documents/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Document>> getDocuments(@PathVariable Long id) {
        List<Document> documents = prospectService.getDocuments(id);
        return ResponseEntity.ok(documents);
    }
}
