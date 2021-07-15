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
  @Pattern(regexp = "^([A-Z]{1})([a-z]{1,})$", message = "O nome da propriedade deve começar com uma letra maiúscula.")
  @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres.")
  @JsonProperty("prop_name")
  private String name;

  @NotNull(message = "O district não pode ser nulo.")
  @NotEmpty(message = "O district não pode ser vazio.")
  @Valid
  private DistrictForm district;

  @NotNull(message = "Os rooms não pode ser nulos.")
  @NotEmpty(message = "Os rooms não podem ser vazios.")
  @Valid
  private List<RoomForm> rooms;
}
