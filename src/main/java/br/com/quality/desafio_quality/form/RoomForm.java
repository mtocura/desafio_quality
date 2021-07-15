package br.com.quality.desafio_quality.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomForm {

  @NotEmpty(message = "O campo não pode estar vazio.")
  @Pattern(regexp = "^([A-Z]{1})([a-z]{1,})$", message = "O nome do cômodo deve começar com uma letra maiúscula.")
  @Size(max = 30, message = "O comprimento do cômodo não pode exceder 30 caracteres.")
  @JsonProperty("room_name")
  private String name;

  @NotEmpty(message = "A largura do cômodo não pode estar vazia.")
  @Pattern(regexp = "[0-25]", message = "A largura máxima permitida por cômodo é 25 metros.")
  @JsonProperty("room_width")
  private double width;

  @NotEmpty(message = "O comprimento do cômodo não pode estar vazio.")
  @Pattern(regexp = "[0-33]", message = "O comprimento máximo permitido por cômodo é 33 metros.")
  @JsonProperty("room_length")
  private double length;
}
