package br.com.quality.desafio_quality.controller;

import br.com.quality.desafio_quality.converter.DistrictConverter;
import br.com.quality.desafio_quality.entity.District;
import br.com.quality.desafio_quality.form.DistrictForm;
import br.com.quality.desafio_quality.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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
    public ResponseEntity<?> create(@Valid @RequestBody DistrictForm districtForm, UriComponentsBuilder uriBuilder) {
        District district = this.districtService.create(DistrictConverter.districtFormToEntity(districtForm));
        URI uri = uriBuilder.path("/api/districts/{id}").buildAndExpand(district.getId()).toUri();
        return ResponseEntity.created(uri).build();
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
