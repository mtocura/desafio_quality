package br.com.quality.desafio_quality.utils.area;

import java.util.List;

public final class AreaUtil {
  private AreaUtil() {
  }

  public static double calculate(double width, double length) {
    return width * length;
  }

  public static double calculateTotalArea(List<Double> areas) {
    return areas.stream().reduce(0.0, (a, b) -> a + b);
  }
}
