package br.com.quality.desafio_quality.service;

import br.com.quality.desafio_quality.dto.RoomDTO;
import br.com.quality.desafio_quality.entity.House;
import br.com.quality.desafio_quality.entity.Room;
import br.com.quality.desafio_quality.exception.HouseNotFoundException;
import br.com.quality.desafio_quality.repository.HouseRepository;
import br.com.quality.desafio_quality.utils.AreaUtil;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class HouseService {

    private HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public House get(long id) {
        Optional<House> optionalHouse = this.houseRepository.findById(id);

        if(optionalHouse.isPresent()) {
            return optionalHouse.get();
        }

        throw new HouseNotFoundException("Propriedade não encontrada.");
    }

    public List<House> get() {
        return this.houseRepository.findAll();
    }

    public House create(House house) {
        return this.houseRepository.save(house);
    }

    public void delete(long id) {
        this.houseRepository.deleteById(id);
    }

    public double calculateArea(House house) {
        List<Room> rooms = house.getRooms();
        List<Double> areas = rooms
                .stream()
                .map(room -> AreaUtil.calculate(room.getWidth(), room.getLength()))
                .collect(Collectors.toList());
        return AreaUtil.calculateTotalArea(areas);
    }

    public double calculatePrice(double areaPrice, double totalAreaM2) {
        return areaPrice * totalAreaM2;
    }

    public List<RoomDTO> getRoomsAreas(long houseId) {
        House house = get(houseId);
        List<RoomDTO> rooms = new ArrayList<>();

        for (Room room : house.getRooms()) {
            rooms.add(new RoomDTO(room.getName(), AreaUtil.calculate(room.getWidth(), room.getLength())));
        }

        return rooms;
    }

    public RoomDTO largestRoom(long id) {
        Optional<House> h = this.houseRepository.findById(id);
        if (h.isEmpty()) {
            throw new HouseNotFoundException("A casa " + id + " não foi encontrada");
        }
        Room r = h
                .get()
                .getRooms()
                .stream()
                .reduce(null, HouseService::selectHouse);
        return null;
    }

    private static Room selectHouse(Room a, Room b) {
        if (a == null) {
            return b;
        }

        double aArea = AreaUtil.calculate(a.getWidth(), a.getLength());
        double bArea = AreaUtil.calculate(b.getWidth(), b.getLength());
        if (aArea > bArea) {
            return a;
        }
        return b;
    }
}
