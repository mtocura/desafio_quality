package br.com.quality.desafio_quality.controller;

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
        return ResponseEntity.ok().body(this.houseService);
    }


    @GetMapping("/house")
    public ResponseEntity<?> getAllHouses() {
        return ResponseEntity.ok().body(this.houseService);
    }

    @GetMapping("/house/{id}")
    public ResponseEntity<?> getHouseById(@PathVariable long id) {
        return ResponseEntity.ok(this.houseService);
    }
}
