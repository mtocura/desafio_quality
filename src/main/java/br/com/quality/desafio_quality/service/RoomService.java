package br.com.quality.desafio_quality.service;

import br.com.quality.desafio_quality.dto.RoomDTO;
import br.com.quality.desafio_quality.entity.Room;
import br.com.quality.desafio_quality.form.RoomForm;
import br.com.quality.desafio_quality.repository.RoomRepository;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room create(Room room) {
        Room createdRoom = this.roomRepository.save(room);
        return createdRoom;
    }

    public void delete(Room room) {
        this.roomRepository.delete(room);
    }

    public Room get(long id) {
        return this.roomRepository.getById(id);
    }

}
