package br.com.quality.desafio_quality.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "district")
@Data
@NoArgsConstructor
public class District {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private BigDecimal valueM2;

  public District(String name, BigDecimal valueM2) {
    this.name = name;
    this.valueM2 = valueM2;
  }
}
