package br.com.quality.desafio_quality.controller;

import br.com.quality.desafio_quality.converter.HouseConverter;
import br.com.quality.desafio_quality.form.HouseForm;
import br.com.quality.desafio_quality.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<?> createHouse(@Valid @RequestBody HouseForm houseForm) {
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
    public void updateHouse(@PathVariable long id, @Valid @RequestBody HouseForm houseForm) {
       houseService.update(id, (HouseConverter.houseFormToEntity(houseForm)));
    }

    @DeleteMapping("/house/{id}")
    public void deleteHouse (@PathVariable long id) {
        this.houseService.delete(id);
    }

    @GetMapping("/house/{id}/area")
    public ResponseEntity<?> getCalculateArea(@PathVariable long id) {

        return ResponseEntity.ok().body(this.houseService.getHouseArea(id));
    }

    @GetMapping("/house/{id}/price")
    public ResponseEntity<?> calculatePrice(@PathVariable long id) {

        return ResponseEntity.ok().body(this.houseService.getHousePrice(id));
    }

    @GetMapping("/house/{id}/areas/rooms")
    public ResponseEntity<?> getRoomsAreas(@PathVariable long id) {

        return ResponseEntity.ok().body(this.houseService.getRoomsAreas(id));
    }

}
