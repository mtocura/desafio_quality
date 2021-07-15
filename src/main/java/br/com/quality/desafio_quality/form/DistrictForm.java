package br.com.quality.desafio_quality.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictForm {
  @JsonProperty("prop_district")
  private String name;
  @JsonProperty("value_distric_m2")
  private BigDecimal value;
}
