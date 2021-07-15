package br.com.quality.desafio_quality.service;

import br.com.quality.desafio_quality.dto.HouseSizeDTO;
import br.com.quality.desafio_quality.dto.HouseValueDTO;
import br.com.quality.desafio_quality.dto.RoomDTO;
import br.com.quality.desafio_quality.entity.House;
import br.com.quality.desafio_quality.entity.Room;
import br.com.quality.desafio_quality.exception.HouseNotFoundException;
import br.com.quality.desafio_quality.form.HouseForm;
import br.com.quality.desafio_quality.form.RoomForm;
import br.com.quality.desafio_quality.repository.HouseRepository;
import br.com.quality.desafio_quality.utils.AreaUtil;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public HouseSizeDTO getHouseArea(long houseId) {
        House house = get(houseId);
        List<Room> rooms = house.getRooms();
        List<Double> areas = rooms
                .stream()
                .map(room -> AreaUtil.calculate(room.getWidth(), room.getLength()))
                .collect(Collectors.toList());
        return new HouseSizeDTO(house.getName(), AreaUtil.calculateTotalArea(areas));
    }

    public HouseValueDTO getHousePrice(long houseId) {
        House house = get(houseId);
        BigDecimal districtValue = house.getDistrict().getValueM2();

        HouseSizeDTO houseTotalArea = getHouseArea(houseId);

        BigDecimal totalPrice = districtValue.multiply(BigDecimal.valueOf(houseTotalArea.getM2()));

        return new HouseValueDTO(house.getName(), houseTotalArea.getM2(), totalPrice);
    }

    public List<RoomDTO> getRoomsAreas(long houseId) {
        House house = get(houseId);
        List<RoomDTO> rooms = new ArrayList<>();

        for (Room room : house.getRooms()) {
            rooms.add(new RoomDTO(room.getName(), AreaUtil.calculate(room.getWidth(), room.getLength())));
        }

        return rooms;
    }
}
