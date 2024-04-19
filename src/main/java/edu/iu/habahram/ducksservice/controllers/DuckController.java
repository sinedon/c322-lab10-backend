package edu.iu.habahram.ducksservice.controllers;

import edu.iu.habahram.ducksservice.model.DuckData;
import edu.iu.habahram.ducksservice.repository.DucksFileRepository;
import edu.iu.habahram.ducksservice.repository.DucksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ducks")
public class DuckController {

    private DucksRepository ducksRepository;
    private DucksFileRepository ducksFileRepository;

    public DuckController(DucksRepository ducksRepository) {
        this.ducksRepository = ducksRepository;
    }

    @PostMapping
    public DuckData add(@RequestBody DuckData duck) {
       return ducksRepository.save(duck);
   }

    @GetMapping
    public List<DuckData> findAll() {
        return ducksRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DuckData> find(@PathVariable int id) {
        DuckData duck = ducksRepository.findById(id);
        if(duck != null) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(duck);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PostMapping("/{id}/image")
    public boolean updateImage(@PathVariable int id,
                               @RequestParam MultipartFile file) throws IOException {
        return ducksFileRepository.updateImage(id, file);
    }

    @PostMapping("/{id}/audio")
    public boolean updateAudio(@PathVariable int id,
                               @RequestParam MultipartFile file) throws IOException {
        return ducksFileRepository.updateAudio(id, file);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<?> getImage(@PathVariable int id) throws IOException {
        byte[] image = ducksFileRepository.getImage(id);
        return ResponseEntity.status(HttpStatus.FOUND)
                .contentType(MediaType.IMAGE_PNG)
                .body(image);
    }

    @GetMapping("/{id}/audio")
    public ResponseEntity<?> getAudio(@PathVariable int id) throws IOException {
        byte[] image = ducksFileRepository.getAudio(id);
        return ResponseEntity.status(HttpStatus.FOUND)
                .contentType(MediaType.valueOf("audio/mp3"))
                .body(image);
    }

}
