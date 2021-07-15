package br.com.quality.desafio_quality.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseForm {

  @NotNull(message = "O nome da propriedade não pode ser nulo.")
  @NotEmpty(message = "O nome da propriedade não pode estar vazio.")
  @Pattern(regexp = "^(([A-ZÁÀÂÃÉÍÓÔÕÚÇ])([a-záàâãéêíóôõúç])+\\s?)+$", message = "O nome da propriedade deve começar com uma letra maiúscula.")
  @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres.")
  @JsonProperty("prop_name")
  private String name;

  @Valid
  @NotNull(message = "O district não pode ser nulo.")
  private DistrictForm district;

  @Valid
  @NotNull(message = "Os rooms não pode ser nulos.")
  private List<RoomForm> rooms;
}
