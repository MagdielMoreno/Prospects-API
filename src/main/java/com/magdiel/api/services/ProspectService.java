package com.magdiel.api.services;

import java.util.List;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.magdiel.api.models.Document;
import com.magdiel.api.models.Prospect;
import com.magdiel.api.repositories.DocumentRepo;
import com.magdiel.api.repositories.ProspectRepo;
import jakarta.transaction.Transactional;

@Service
public class ProspectService {
    @Autowired
    private ProspectRepo prospectRepo;

    @Autowired
    private DocumentRepo documentRepo;

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    public Prospect add(Prospect prospect){
        return prospectRepo.save(prospect); 
    }

    public Prospect update(Prospect prospect){
        if (prospect.getId() == null){
            throw new IllegalArgumentException("ID is required");
        }

        if (!prospectRepo.existsById(prospect.getId())) {
            throw new IllegalArgumentException("Prospect to update not found");
        }

        return prospectRepo.save(prospect);
    }

    public void delete(Long id){ 
        if (!prospectRepo.existsById(id)) {
            throw new IllegalArgumentException("Prospect not found with id: " + id);
        }
        prospectRepo.deleteById(id); 
    }

    public Prospect find(Long id){ 
        Prospect prospect = prospectRepo.findProspectById(id);
        if (prospect == null) {
            throw new IllegalArgumentException("Prospect not found with id: " + id);
        }
        return prospect;
    }

    @Transactional
    public List<Prospect> findAll(){
        return prospectRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public void uploadFile(Long prospectId, String name, MultipartFile file) throws IOException{
        if (file.isEmpty()) {
            throw new IllegalArgumentException("No file has been uploaded!");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("File size must be less than 10MB.");
        }

        Prospect prospect = prospectRepo.findProspectById(prospectId);

        if (prospect != null) {
            documentRepo.save(new Document(prospectId, name, file.getBytes()));
        } else {
            throw new IllegalArgumentException("Prospect not found with id: " + prospectId);
        }
    }

    public List<Document> getDocuments(Long id) {
        List<Document> documents = documentRepo.findByProspectId(id);

        if (documents.isEmpty()) {
            throw new IllegalArgumentException("No documents found for prospect with id: " + id);
        }
    
        return documents;
    }
}
