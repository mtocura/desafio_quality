package br.com.quality.desafio_quality.converter;

import br.com.quality.desafio_quality.entity.House;
import br.com.quality.desafio_quality.form.HouseForm;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class HouseConverter {
  private static ModelMapper mapper = new ModelMapper();

  public static House houseFormToEntity(HouseForm houseForm) {
    return mapper.map(houseForm, House.class);
  }

  public static List<House> houseFormToEntity(List<HouseForm> houseForms) {
    List<House> houses = new ArrayList<>();

    for (HouseForm houseForm : houseForms) {
      houses.add(houseFormToEntity(houseForm));
    }

    return houses;
  }
}
