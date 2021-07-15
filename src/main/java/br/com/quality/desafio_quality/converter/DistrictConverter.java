package br.com.quality.desafio_quality.converter;

import br.com.quality.desafio_quality.entity.District;
import br.com.quality.desafio_quality.form.DistrictForm;

import java.util.ArrayList;
import java.util.List;

public class DistrictConverter {

  public static District districtFormToEntity(DistrictForm districtForm) {
    return new District(
            districtForm.getName(),
            districtForm.getValue(),
            null
    );
  }

  public static List<District> districtFormToEntity(List<DistrictForm> districtForms) {
    List<District> districts = new ArrayList<>();

    for (DistrictForm districtForm : districtForms) {
      districts.add(districtFormToEntity(districtForm));
    }

    return districts;
  }
}
