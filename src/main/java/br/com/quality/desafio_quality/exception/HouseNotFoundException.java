package br.com.quality.desafio_quality.exception;

public class HouseNotFoundException extends RuntimeException {
  public static final String DEFAULT_MESSAGE = "Propriedade não encontrada.";

  public HouseNotFoundException() {
    this(DEFAULT_MESSAGE);
  }

  public HouseNotFoundException(String message) {
    super(message);
  }
}
