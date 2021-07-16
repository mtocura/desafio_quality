package br.com.quality.desafio_quality.exception;

public class DistrictNotFoundException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "Distrito não encontrado";

    public DistrictNotFoundException() {
        this(DistrictNotFoundException.DEFAULT_MESSAGE);
    }

    public DistrictNotFoundException(String message) {
        super(message);
    }
}
