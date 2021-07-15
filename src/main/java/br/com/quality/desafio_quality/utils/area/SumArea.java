package br.com.quality.desafio_quality.utils.area;

import java.util.List;

public class SumArea {
  public static double calcTotalArea(List<Double> areas) {
    return areas.stream().reduce(0.0, (a, b) -> a + b);
  }
}
