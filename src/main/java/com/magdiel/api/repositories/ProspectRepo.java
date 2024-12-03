package com.magdiel.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.magdiel.api.models.Prospect;

@Repository
public interface ProspectRepo extends JpaRepository<Prospect, Long>{
    Prospect findProspectById(Long id);
}
