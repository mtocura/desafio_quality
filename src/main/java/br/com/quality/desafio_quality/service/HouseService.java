package br.com.quality.desafio_quality.service;

import br.com.quality.desafio_quality.entity.House;
import br.com.quality.desafio_quality.entity.Room;
import br.com.quality.desafio_quality.repository.HouseRepository;
import br.com.quality.desafio_quality.utils.AreaUtil;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return this.houseRepository.getById(id);
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
}
