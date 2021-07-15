package br.com.quality.desafio_quality.converter;

import br.com.quality.desafio_quality.dto.DistrictDTO;
import br.com.quality.desafio_quality.entity.District;
import br.com.quality.desafio_quality.form.DistrictForm;

import java.util.ArrayList;
import java.util.List;

public class DistrictConverter {

  public static District districtFormToEntity(DistrictForm districtForm) {
    return new District(
            districtForm.getName(),
            districtForm.getValue()
    );
  }

  public static List<District> districtFormToEntity(List<DistrictForm> districtForms) {
    List<District> districts = new ArrayList<>();

    for (DistrictForm districtForm : districtForms) {
      districts.add(districtFormToEntity(districtForm));
    }

    return districts;
  }

  public static DistrictDTO districtEntityToDTO(District district) {
    return new DistrictDTO(
            district.getId(),
            district.getName(), 
            district.getValueM2()
    );
  }

  public static List<DistrictDTO> districtEntityToDTO(List<District> districts) {
    List<DistrictDTO> districtDTOS = new ArrayList<>();

    for (District district : districts) {
      districtDTOS.add(districtEntityToDTO(district));
    }

    return districtDTOS;
  }
}
