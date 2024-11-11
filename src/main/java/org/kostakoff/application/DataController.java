package org.kostakoff.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/data")
public class DataController {

    @Autowired
    private DataRepository dataRepository;

    @GetMapping
    public List<Data> getAllData() {
        return dataRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Data> getDataById(@PathVariable Long id) {
        Optional<Data> data = dataRepository.findById(id);
        if (data.isPresent()) {
            Data foundData = data.get();
            foundData.setContent(new String(Base64.getDecoder().decode(foundData.getContent())));
            return ResponseEntity.ok(foundData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Data> updateData(@PathVariable Long id, @RequestBody Data newData) {
        return dataRepository.findById(id)
                .map(foundData -> {
                    foundData.setUrl(newData.getUrl());
                    foundData.setContent(Base64.getEncoder().encodeToString(newData.getContent().getBytes()));
                    Data updatedData = dataRepository.save(foundData);
                    updatedData.setContent(new String(Base64.getDecoder().decode(updatedData.getContent())));
                    return ResponseEntity.ok(updatedData);
                }).orElseGet(() -> {
                    newData.setId(id);
                    newData.setContent(Base64.getEncoder().encodeToString(newData.getContent().getBytes()));
                    Data createdData = dataRepository.save(newData);
                    createdData.setContent(new String(Base64.getDecoder().decode(createdData.getContent())));
                    return ResponseEntity.ok(createdData);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        if (dataRepository.existsById(id)) {
            dataRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}