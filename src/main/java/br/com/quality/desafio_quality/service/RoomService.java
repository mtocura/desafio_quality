package br.com.quality.desafio_quality.service;

import br.com.quality.desafio_quality.dto.RoomDTO;
import br.com.quality.desafio_quality.entity.Room;
import br.com.quality.desafio_quality.form.RoomForm;
import br.com.quality.desafio_quality.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room createRoom(RoomForm roomForm) {
        ModelMapper modelMapper = new ModelMapper();
        Room room = modelMapper.map(roomForm, Room.class);
        Room createdRoom = this.roomRepository.save(room);
        return createdRoom;
    }

    public void deleteRoom(RoomForm roomForm) {
        ModelMapper modelMapper = new ModelMapper();
        Room room = modelMapper.map(roomForm, Room.class);
        this.roomRepository.delete(room);
    }

    public RoomDTO getRoom(long id) {
        ModelMapper modelMapper = new ModelMapper();
        return new RoomDTO();
    }

}
