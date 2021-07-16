package br.com.quality.desafio_quality.form;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseUpdateForm {

    @NotNull(message = "O nome da propriedade não pode ser nulo.")
    @NotEmpty(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp = "^(([A-ZÁÀÂÃÉÍÓÔÕÚÇ])([a-záàâãéêíóôõúç])+\\s?)+$", message = "O nome da propriedade deve começar com uma letra maiúscula.")
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres.")
    @JsonProperty("prop_name")
    private String name;

    @NotNull(message = "O district não pode ser nulo.")
    @Min(value = 1, message = "O id do bairro deve ser maior que 0.")
    private Long districtId;

    @Valid
    @NotNull(message = "Os rooms não pode ser nulos.")
    private List<RoomUpdateForm> rooms;
}
