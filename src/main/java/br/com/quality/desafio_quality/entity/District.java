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
  @OneToMany(
          mappedBy = "district",
          cascade = CascadeType.ALL,
          orphanRemoval = true)
  private List<House> houses;

  public District(String name, BigDecimal valueM2, List<House> houses) {
    this.name = name;
    this.valueM2 = valueM2;
    this.houses = houses;
  }
}
