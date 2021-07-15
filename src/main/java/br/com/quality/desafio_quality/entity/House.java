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
  private Long districtId;
  @OneToMany(
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  @JoinColumn(name = "house_id")
  private List<Room> rooms;

  public House(String name, Long districtId, List<Room> rooms) {
    this.name = name;
    this.districtId = districtId;
    this.rooms = rooms;
  }
}
