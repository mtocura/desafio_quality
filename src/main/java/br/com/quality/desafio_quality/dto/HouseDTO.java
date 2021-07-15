package br.com.quality.desafio_quality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDTO {
  private Long id;
  private String name;
  private DistrictDTO district;
  private List<RoomDTO> rooms;
}
