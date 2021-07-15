package br.com.quality.desafio_quality.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDTO {
  private Long id;
  private String name;
  @JsonProperty("value_m2")
  private BigDecimal value;
}
