package br.com.quality.desafio_quality.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseForm {
  @JsonProperty("prop_name")
  private String name;
  private DistrictForm district;
  private List<RoomForm> rooms;
}
