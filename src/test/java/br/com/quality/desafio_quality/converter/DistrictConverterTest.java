package br.com.quality.desafio_quality.converter;

import br.com.quality.desafio_quality.dto.DistrictDTO;
import br.com.quality.desafio_quality.entity.District;
import br.com.quality.desafio_quality.form.DistrictForm;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class DistrictConverterTest {

  @Test
  public void shouldReturnAnInstanceOfDistrictEntity() {
    DistrictForm districtForm = new DistrictForm("Bairro Mock", BigDecimal.valueOf(12345.67));
    District expectedDistric = new District("Bairro Mock", BigDecimal.valueOf(12345.67));

    District responseDistrict = DistrictConverter.districtFormToEntity(districtForm);

    Assertions.assertNotNull(responseDistrict);
    assertThat(responseDistrict).usingRecursiveComparison().isEqualTo(expectedDistric);
  }

  @Test
  public void shoulReturnAListOfDistrictEntity() {
    DistrictForm districtForm1 = new DistrictForm("Bairro Mock I", BigDecimal.valueOf(1234.56));
    DistrictForm districtForm2 = new DistrictForm("Bairro Mock II", BigDecimal.valueOf(12345));
    List<DistrictForm> districtForms = new ArrayList<>(Arrays.asList(districtForm1, districtForm2));

    District district1 = new District("Bairro Mock I", BigDecimal.valueOf(1234.56));
    District district2 = new District("Bairro Mock II", BigDecimal.valueOf(12345));
    List<District> expectedDistricts = new ArrayList<>(Arrays.asList(district1, district2));

    List<District> responseDistricts = DistrictConverter.districtFormToEntity(districtForms);

    Assertions.assertNotNull(responseDistricts);
    assertThat(responseDistricts).usingRecursiveComparison().isEqualTo(expectedDistricts);
  }

  @Test
  public void shouldReturnAnInstanceOfDistrictDTO() {
    District district = new District("Bairro Mock", BigDecimal.valueOf(12345.67));
    DistrictDTO expectedDistric = new DistrictDTO(null, "Bairro Mock", BigDecimal.valueOf(12345.67));

    DistrictDTO responseDistrict = DistrictConverter.districtEntityToDTO(district);

    Assertions.assertNotNull(responseDistrict);
    assertThat(responseDistrict).usingRecursiveComparison().isEqualTo(expectedDistric);
  }

  @Test
  public void shoulReturnAListOfDistrictDTO() {
    District district1 = new District("Bairro Mock I", BigDecimal.valueOf(1234.56));
    District district2 = new District("Bairro Mock II", BigDecimal.valueOf(12345));
    List<District> districts = new ArrayList<>(Arrays.asList(district1, district2));

    DistrictDTO districtDTO1 = new DistrictDTO(null, "Bairro Mock I", BigDecimal.valueOf(1234.56));
    DistrictDTO districtDTO2 = new DistrictDTO(null, "Bairro Mock II", BigDecimal.valueOf(12345));
    List<DistrictDTO> expectedDistricts = new ArrayList<>(Arrays.asList(districtDTO1, districtDTO2));

    List<DistrictDTO> responseDistricts = DistrictConverter.districtEntityToDTO(districts);

    Assertions.assertNotNull(responseDistricts);
    assertThat(responseDistricts).usingRecursiveComparison().isEqualTo(expectedDistricts);
  }
}
