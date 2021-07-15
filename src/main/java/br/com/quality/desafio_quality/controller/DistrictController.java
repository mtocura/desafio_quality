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

    @PostMapping("/district")
    public ResponseEntity<?> createDistrict(@Valid @RequestBody DistrictForm districtForm) {
        return ResponseEntity.ok().body(this.districtService.create(DistrictConverter.districtFormToEntity(districtForm)));
    }

    @GetMapping("/district")
    public ResponseEntity<?> getAllDistricts() {
        return ResponseEntity.ok().body(this.districtService.get());
    }

    @GetMapping("/district/{id}")
    public ResponseEntity<?> getDistrictById(@PathVariable long id) {
        return ResponseEntity.ok(this.districtService.get(id));
    }

    @PutMapping("/district/{id}")
    public void updateDistrict(@PathVariable long id, @Valid @RequestBody DistrictForm districtForm) {
        districtService.update(id, DistrictConverter.districtFormToEntity(districtForm));
    }

    @DeleteMapping("/district/{id}")
    public void deleteDistrict (@PathVariable long id) {
        this.districtService.delete(id);
    }
}
