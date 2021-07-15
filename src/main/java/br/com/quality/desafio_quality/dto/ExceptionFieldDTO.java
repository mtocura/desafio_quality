package br.com.quality.desafio_quality.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ExceptionFieldDTO {

    @JsonProperty("ValidationError")
    private Map<String, String> validationErrors;

    public ExceptionFieldDTO(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }
}
