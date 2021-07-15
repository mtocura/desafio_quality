package br.com.quality.desafio_quality.exception;

public class HouseNotExistsException extends RuntimeException{
  public HouseNotExistsException(String message) {
    super(message);
  }
}
