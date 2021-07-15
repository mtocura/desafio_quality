package br.com.quality.desafio_quality.converter;

import br.com.quality.desafio_quality.dto.HouseDTO;
import br.com.quality.desafio_quality.dto.RoomDTO;
import br.com.quality.desafio_quality.entity.House;
import br.com.quality.desafio_quality.entity.Room;
import br.com.quality.desafio_quality.form.HouseForm;
import br.com.quality.desafio_quality.form.RoomForm;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class HouseConverterTest {
  @Test
  public void shouldReturnAnInstanceOfHouseEntity() {
    HouseForm houseForm = new HouseForm("Propriedade Mock", 1l, createRoomFormList());
    House expectedHouse = new House("Propriedade Mock", 1l, createRoomList());

    House responseHouse = HouseConverter.houseFormToEntity(houseForm);

    Assertions.assertNotNull(responseHouse);
    assertThat(responseHouse).usingRecursiveComparison().isEqualTo(expectedHouse);
  }

  @Test
  public void shouldReturnAListOfHouseEntity() {
    HouseForm houseForm1 = new HouseForm("Casa", 1l, createRoomFormList());
    HouseForm houseForm2 = new HouseForm("Apartamento", 3l, createRoomFormList());
    List<HouseForm> houseForms = new ArrayList<>(Arrays.asList(houseForm1, houseForm2));

    House house1 = new House("Casa", 1l, createRoomList());
    House house2 = new House("Apartamento", 3l, createRoomList());
    List<House> expectedHouses = new ArrayList<>(Arrays.asList(house1, house2));

    List<House> responseHouses = HouseConverter.houseFormToEntity(houseForms);

    Assertions.assertNotNull(responseHouses);
    assertThat(responseHouses).usingRecursiveComparison().isEqualTo(expectedHouses);
  }

  @Test
  public void shouldReturnAnInstanceOfHouseDTO() {
    House house = new House("Propriedade Mock", 1l, createRoomList());
    HouseDTO expectedHouse = new HouseDTO(null, "Propriedade Mock", 1l, createRoomDTOList());

    HouseDTO responseHouse = HouseConverter.houseEntityToDTO(house);

    Assertions.assertNotNull(responseHouse);
    assertThat(responseHouse).usingRecursiveComparison().isEqualTo(expectedHouse);
  }

  @Test
  public void shouldReturnAListOfHouseDTO() {
    House house1 = new House("Casa", 1l, createRoomList());
    House house2 = new House("Apartamento", 3l, createRoomList());
    List<House> houses = new ArrayList<>(Arrays.asList(house1, house2));

    HouseDTO houseDTO1 = new HouseDTO(null, "Casa", 1l, createRoomDTOList());
    HouseDTO houseDTO2 = new HouseDTO(null, "Apartamento", 3l, createRoomDTOList());
    List<HouseDTO> expectedHouses = new ArrayList<>(Arrays.asList(houseDTO1, houseDTO2));

    List<HouseDTO> responseHouses = HouseConverter.houseEntityToDTO(houses);

    Assertions.assertNotNull(responseHouses);
    assertThat(responseHouses).usingRecursiveComparison().isEqualTo(expectedHouses);
  }

  private List<RoomForm> createRoomFormList() {
    RoomForm roomForm1 = new RoomForm("Sala", 3.5, 2);
    RoomForm roomForm2 = new RoomForm("Quarto", 2.5, 2);
    return new ArrayList<>(Arrays.asList(roomForm1, roomForm2));
  }

  private List<Room> createRoomList() {
    Room room1 = new Room("Sala", 3.5, 2);
    Room room2 = new Room("Quarto", 2.5, 2);
    return new ArrayList<>(Arrays.asList(room1, room2));
  }

  private List<RoomDTO> createRoomDTOList() {
    RoomDTO roomDTO1 = new RoomDTO(null, "Sala", 3.5, 2);
    RoomDTO roomDTO2 = new RoomDTO(null, "Quarto", 2.5, 2);
    return new ArrayList<>(Arrays.asList(roomDTO1, roomDTO2));
  }
}
