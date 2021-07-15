package br.com.quality.desafio_quality.converter;

import br.com.quality.desafio_quality.dto.HouseDTO;
import br.com.quality.desafio_quality.entity.House;
import br.com.quality.desafio_quality.form.HouseForm;

import java.util.ArrayList;
import java.util.List;

public class HouseConverter {

  public static House houseFormToEntity(HouseForm houseForm) {
    return new House(
            houseForm.getName(),
            houseForm.getDistrictId(),
            RoomConverter.roomFormToEntity(houseForm.getRooms())
    );
  }

  public static List<House> houseFormToEntity(List<HouseForm> houseForms) {
    List<House> houses = new ArrayList<>();

    for (HouseForm houseForm : houseForms) {
      houses.add(houseFormToEntity(houseForm));
    }

    return houses;
  }

  public static HouseDTO houseEntityToDTO(House house) {
    return new HouseDTO(
            house.getId(),
            house.getName(),
            house.getDistrictId(),
            RoomConverter.roomEntityToDTO(house.getRooms())
    );
  }

  public static List<HouseDTO> houseEntityToDTO(List<House> houses) {
    List<HouseDTO> houseDTOS = new ArrayList<>();

    for (House house : houses) {
      houseDTOS.add(houseEntityToDTO(house));
    }

    return houseDTOS;
  }
}
