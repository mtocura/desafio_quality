package br.com.quality.desafio_quality.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomForm {
  @JsonProperty("room_name")
  private String name;
  @JsonProperty("room_width")
  private double width;
  @JsonProperty("room_length")
  private double length;
}
