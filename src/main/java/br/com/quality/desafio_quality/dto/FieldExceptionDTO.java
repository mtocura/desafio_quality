package br.com.quality.desafio_quality.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class FieldExceptionDTO {

    @JsonProperty("ValidationError")
    private Map<String, String> validationErrors;
}
