package br.com.quality.desafio_quality.converter;

import br.com.quality.desafio_quality.entity.House;
import br.com.quality.desafio_quality.form.HouseForm;

import java.util.ArrayList;
import java.util.List;

public class HouseConverter {

  public static House houseFormToEntity(HouseForm houseForm) {
    return new House(
            houseForm.getName(),
            DistrictConverter.districtFormToEntity(houseForm.getDistrict()),
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
}
