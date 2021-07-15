package br.com.quality.desafio_quality.converter;

import br.com.quality.desafio_quality.entity.District;
import br.com.quality.desafio_quality.form.DistrictForm;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DistrictConverter {
  private static ModelMapper mapper = new ModelMapper();

  public static District districtFormToEntity(DistrictForm districtForm) {
    return mapper.map(districtForm, District.class);
  }

  public static List<District> districtFormToEntity(List<DistrictForm> districtForms) {
    List<District> districts = new ArrayList<>();

    for (DistrictForm districtForm : districtForms) {
      districts.add(districtFormToEntity(districtForm));
    }

    return districts;
  }
}
