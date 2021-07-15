package br.com.quality.desafio_quality.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "house")
@Data
@NoArgsConstructor
public class House {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @ManyToOne
  @JoinColumn(name = "district_id")
  private District district;
  @OneToMany(
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  @JoinColumn(name = "house_id")
  private List<Room> rooms;

  public House(String name, District district, List<Room> rooms) {
    this.name = name;
    this.district = district;
    this.rooms = rooms;
  }
}
