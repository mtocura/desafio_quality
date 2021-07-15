package br.com.quality.desafio_quality.exception;

public class HouseNotFoundException extends RuntimeException {
  public HouseNotFoundException(String message) {
    super(message);
  }
}
