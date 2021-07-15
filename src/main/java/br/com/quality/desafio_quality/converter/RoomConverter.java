package br.com.quality.desafio_quality.converter;

import br.com.quality.desafio_quality.entity.Room;
import br.com.quality.desafio_quality.form.RoomForm;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class RoomConverter {
  private static ModelMapper mapper = new ModelMapper();

  public static Room roomFormToEntity(RoomForm roomForm) {
    return mapper.map(roomForm, Room.class);
  }

  public static List<Room> roomFormToEntity(List<RoomForm> roomForms) {
    List<Room> rooms = new ArrayList<>();

    for (RoomForm roomForm : roomForms) {
      rooms.add(roomFormToEntity(roomForm));
    }

    return rooms;
  }
}
