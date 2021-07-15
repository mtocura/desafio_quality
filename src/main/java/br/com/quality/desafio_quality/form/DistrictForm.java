package br.com.quality.desafio_quality.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictForm {

  @NotNull(message = "O nome do bairro não pode ser nulo.")
  @NotEmpty(message = "O nome do bairro não pode estar vazio.")
  @Pattern(regexp = "^(([A-ZÁÀÂÃÉÍÓÔÕÚÇ])([a-záàâãéêíóôõúç])+\\s?)+$", message = "O nome do bairro deve ser composto por letras.")
  @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres.")
  @JsonProperty("prop_district")
  private String name;

  @NotNull(message = "O valor não pode ser nulo.")
  @DecimalMin(value = "0.0", inclusive = false)
  @DecimalMax(value="9999999999999.99", message = "O valor não pode ter mais de 13 dígitos.")
  @JsonProperty("value_district_m2")
  private BigDecimal value;
}
