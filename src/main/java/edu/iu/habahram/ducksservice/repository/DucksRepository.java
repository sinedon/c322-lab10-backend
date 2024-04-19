package edu.iu.habahram.ducksservice.repository;

import edu.iu.habahram.ducksservice.model.DuckData;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface DucksRepository extends CrudRepository<DuckData, String> {
    DuckData findById(int id);
    List<DuckData> findAll();
}
