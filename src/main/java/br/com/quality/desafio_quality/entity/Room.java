package br.com.quality.desafio_quality.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "room")
@Data
@NoArgsConstructor
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private double width;
  private double length;

  public Room(String name, double width, double length) {
    this.name = name;
    this.width = width;
    this.length = length;
  }
}
