package br.com.quality.desafio_quality.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomUpdateForm {

    @NotNull(message = "O id do room não pode ser nulo.")
    @JsonProperty("room_id")
    private Long id;

    @NotNull(message = "O nome do room não pode ser nulo.")
    @NotEmpty(message = "O campo não pode estar vazio.")
    @Pattern(regexp = "^(([A-ZÁÀÂÃÉÍÓÔÕÚÇ])([a-záàâãéêíóôõúç])+\\s?)+$", message = "O nome do cômodo deve começar com uma letra maiúscula.")
    @Size(max = 30, message = "O comprimento do cômodo não pode exceder 30 caracteres.")
    @JsonProperty("room_name")
    private String name;

    @NotNull(message = "A largura do cômodo não pode ser nula.")
    @Min(value = 1, message = "A largura mínima permitida deve ser maior que 0.")
    @Max(value = 25, message = "A largura máxima permitida por cômodo é 25 metros.")
    @JsonProperty("room_width")
    private double width;

    @NotNull(message = "O comprimento do cômodo não pode ser nulo.")
    @Min(value = 1, message = "O comprimento mínimo deve ser maior que 0")
    @Max(value = 33, message = "O comprimento máximo permitido por cômodo é 33 metros.")
    @JsonProperty("room_length")
    private double length;
}

