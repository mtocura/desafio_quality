package br.com.quality.desafio_quality.controller;

import br.com.quality.desafio_quality.converter.HouseConverter;
import br.com.quality.desafio_quality.form.HouseForm;
import br.com.quality.desafio_quality.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HouseController {

    private HouseService houseService;

    public HouseController() {
    }

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping("/house")
    public ResponseEntity<?> createHouse(@RequestBody HouseForm houseForm) {
        return ResponseEntity.ok().body(this.houseService.create(HouseConverter.houseFormToEntity(houseForm)));
    }

    @GetMapping("/house")
    public ResponseEntity<?> getAllHouses() {

        return ResponseEntity.ok().body(this.houseService.get());
    }

    @GetMapping("/house/{id}")
    public ResponseEntity<?> getHouseById(@PathVariable long id) {

        return ResponseEntity.ok(this.houseService.get(id));
    }

    @PutMapping("/house/{id}")
    public ResponseEntity<?> updateHouse(@PathVariable long id, @RequestBody HouseForm houseForm) {
        return houseService.update(id, (HouseConverter.houseFormToEntity(houseForm)));
    }

    @DeleteMapping("/house/{id}")
    public void deleteHouse (@PathVariable long id) {
        this.houseService.delete(id);
    }
}
