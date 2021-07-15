package br.com.quality.desafio_quality.controller;

import br.com.quality.desafio_quality.converter.DistrictConverter;
import br.com.quality.desafio_quality.form.DistrictForm;
import br.com.quality.desafio_quality.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class DistrictController {

    private DistrictService districtService;

    public DistrictController() {
    }

    @Autowired
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @PostMapping("/districts")
    public ResponseEntity<?> create(@Valid @RequestBody DistrictForm districtForm) {
        return ResponseEntity.ok().body(this.districtService.create(DistrictConverter.districtFormToEntity(districtForm)));
    }

    @GetMapping("/districts")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(this.districtService.get());
    }

    @GetMapping("/districts/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.districtService.get(id));
    }

    @PutMapping("/districts/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody DistrictForm districtForm) {
        districtService.update(id, DistrictConverter.districtFormToEntity(districtForm));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/districts/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        districtService.delete(id);
        return ResponseEntity.ok().build();
    }
}
