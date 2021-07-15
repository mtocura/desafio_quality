package br.com.quality.desafio_quality.controller;

import br.com.quality.desafio_quality.converter.HouseConverter;
import br.com.quality.desafio_quality.form.HouseForm;
import br.com.quality.desafio_quality.service.HouseService;
import br.com.quality.desafio_quality.service.RoomService;
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

    @PostMapping("/houses")
    public ResponseEntity<?> create(@Valid @RequestBody HouseForm houseForm) {
        return ResponseEntity.ok().body(this.houseService.create(HouseConverter.houseFormToEntity(houseForm)));
    }

    @GetMapping("/houses")
    public ResponseEntity<?> getAll() {

        return ResponseEntity.ok().body(this.houseService.get());
    }

    @GetMapping("/houses/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {

        return ResponseEntity.ok(this.houseService.get(id));
    }

    @PutMapping("/houses/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody HouseForm houseForm) {
        houseService.update(id, (HouseConverter.houseFormToEntity(houseForm)));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/houses/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
       houseService.delete(id);
       return ResponseEntity.ok().build();
    }

    @GetMapping("/houses/{id}/areas")
    public ResponseEntity<?> getCalculateArea(@PathVariable long id) {

        return ResponseEntity.ok().body(this.houseService.getHouseArea(id));
    }

    @GetMapping("/houses/{id}/price")
    public ResponseEntity<?> getCalculatePrice(@PathVariable long id) {

        return ResponseEntity.ok().body(this.houseService.getHousePrice(id));
    }

    @GetMapping("/houses/{id}/areas/rooms")
    public ResponseEntity<?> getRoomsAreas(@PathVariable long id) {

        return ResponseEntity.ok().body(this.houseService.getRoomsAreas(id));
    }

    @GetMapping("/houses/{id}/rooms/area")
    public ResponseEntity<?> getCalculateAreaRoom(@PathVariable long id) {

        return ResponseEntity.ok().body(this.houseService.largestRoom(id));
    }

}
