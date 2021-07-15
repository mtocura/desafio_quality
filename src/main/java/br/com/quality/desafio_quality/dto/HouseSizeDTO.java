package br.com.quality.desafio_quality.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseSizeDTO {
  @JsonProperty("prop_name")
  private String name;
  @JsonProperty("prop_m2")
  private double m2;
}
