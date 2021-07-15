package br.com.quality.desafio_quality.converter;

import br.com.quality.desafio_quality.converter.RoomConverter;
import br.com.quality.desafio_quality.dto.RoomDTO;
import br.com.quality.desafio_quality.entity.Room;
import br.com.quality.desafio_quality.form.RoomForm;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class RoomConverterTest {

  @Test
  public void shouldReturnAnInstanceOfRoomEntity() {
    RoomForm roomForm = new RoomForm("Cômodo Mock", 15.5, 12);
    Room expectedRoom = new Room("Cômodo Mock", 15.5, 12);

    Room responseRoom = RoomConverter.roomFormToEntity(roomForm);

    Assertions.assertNotNull(responseRoom);
    assertThat(responseRoom).usingRecursiveComparison().isEqualTo(expectedRoom);
  }

  @Test
  public void shouldReturnAListOfRoomEntity() {
    RoomForm roomForm1 = new RoomForm("Sala", 3.5, 2);
    RoomForm roomForm2 = new RoomForm("Quarto", 2.5, 2);
    List<RoomForm> roomForms = new ArrayList<>(Arrays.asList(roomForm1, roomForm2));

    Room room1 = new Room("Sala", 3.5, 2);
    Room room2 = new Room("Quarto", 2.5, 2);
    List<Room> expectedRooms = new ArrayList<>(Arrays.asList(room1, room2));

    List<Room> responseRooms = RoomConverter.roomFormToEntity(roomForms);

    Assertions.assertNotNull(responseRooms);
    assertThat(responseRooms).usingRecursiveComparison().isEqualTo(expectedRooms);
  }

  @Test
  public void shouldReturnAnInstanceOfRoomDTO() {
    Room room = new Room("Cômodo Mock", 15.5, 12);
    RoomDTO expectedRoom = new RoomDTO(null, "Cômodo Mock", 15.5, 12);

    RoomDTO responseRoom = RoomConverter.roomEntityToDTO(room);

    Assertions.assertNotNull(responseRoom);
    assertThat(responseRoom).usingRecursiveComparison().isEqualTo(expectedRoom);
  }

  @Test
  public void shouldReturnAListOfRoomDTO() {
    Room room1 = new Room("Sala", 3.5, 2);
    Room room2 = new Room("Quarto", 2.5, 2);
    List<Room> rooms = new ArrayList<>(Arrays.asList(room1, room2));

    RoomDTO roomDTO1 = new RoomDTO(null, "Sala", 3.5, 2);
    RoomDTO roomDTO2 = new RoomDTO(null, "Quarto", 2.5, 2);
    List<RoomDTO> expectedRooms = new ArrayList<>(Arrays.asList(roomDTO1, roomDTO2));

    List<RoomDTO> responseRooms = RoomConverter.roomEntityToDTO(rooms);

    Assertions.assertNotNull(responseRooms);
    assertThat(responseRooms).usingRecursiveComparison().isEqualTo(expectedRooms);
  }
}
