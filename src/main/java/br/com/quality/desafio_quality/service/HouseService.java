package br.com.quality.desafio_quality.service;

import br.com.quality.desafio_quality.dto.HouseSizeDTO;
import br.com.quality.desafio_quality.dto.HouseValueDTO;
import br.com.quality.desafio_quality.dto.RoomSizeDTO;
import br.com.quality.desafio_quality.entity.District;
import br.com.quality.desafio_quality.entity.House;
import br.com.quality.desafio_quality.entity.Room;
import br.com.quality.desafio_quality.exception.HouseNotFoundException;
import br.com.quality.desafio_quality.repository.HouseRepository;
import br.com.quality.desafio_quality.utils.AreaUtil;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class HouseService {

    private HouseRepository houseRepository;
    private DistrictService districtService;

    @Autowired
    public HouseService(HouseRepository houseRepository, DistrictService districtService) {
        this.houseRepository = houseRepository;
        this.districtService = districtService;
    }

    public House get(long id) {
        Optional<House> optionalHouse = this.houseRepository.findById(id);

        if(optionalHouse.isPresent()) {
            return optionalHouse.get();
        }

        throw new HouseNotFoundException();
    }

    public List<House> get() {
        return this.houseRepository.findAll();
    }

    public House create(House house) {
        this.districtService.get(house.getDistrictId());
        return this.houseRepository.save(house);
    }

    public void update(long id, House h) {
        House house = this.get(id);
        house.setDistrictId(h.getDistrictId());
        house.setName(h.getName());
        house.getRooms().clear();
        house.getRooms().addAll(h.getRooms());
        this.houseRepository.save(house);
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
        District district = this.districtService.get(house.getDistrictId());

        BigDecimal districtValue = district.getValueM2();

        HouseSizeDTO houseTotalArea = getHouseArea(houseId);

        BigDecimal totalPrice = districtValue.multiply(BigDecimal.valueOf(houseTotalArea.getM2()));

        return new HouseValueDTO(house.getName(), houseTotalArea.getM2(), totalPrice.setScale(2, RoundingMode.FLOOR));
    }

    public List<RoomSizeDTO> getRoomsAreas(long houseId) {
        House house = get(houseId);
        List<RoomSizeDTO> rooms = new ArrayList<>();

        for (Room room : house.getRooms()) {
            rooms.add(new RoomSizeDTO(room.getName(), AreaUtil.calculate(room.getWidth(), room.getLength())));
        }

        return rooms;
    }

    public RoomSizeDTO largestRoom(long id) {
        House house = this.get(id);
        Room r = house
                .getRooms()
                .stream()
                .reduce(null, HouseService::selectHouse);
        return new RoomSizeDTO(r.getName(), AreaUtil.calculate(r.getWidth(), r.getLength()));
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
