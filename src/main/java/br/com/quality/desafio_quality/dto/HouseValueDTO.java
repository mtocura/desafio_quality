package br.com.quality.desafio_quality.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseValueDTO {
  @JsonProperty("prop_name")
  private String name;
  @JsonProperty("prop_m2")
  private double m2;
  private BigDecimal value;
}
