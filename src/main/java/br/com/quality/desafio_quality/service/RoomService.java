package br.com.quality.desafio_quality.service;

import br.com.quality.desafio_quality.entity.Room;
import br.com.quality.desafio_quality.repository.RoomRepository;
import br.com.quality.desafio_quality.utils.AreaUtil;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room get(long id) {
        return this.roomRepository.getById(id);
    }

    public List<Room> get() {
        return this.roomRepository.findAll();
    }

    public Room create(Room room) {
        return this.roomRepository.save(room);
    }

    public void delete(long id) {
        this.roomRepository.deleteById(id);
    }

    public double calculateArea(double width, double length) {
        return AreaUtil.calculate(width, length);
    }

    public double calculatePrice(double areaPrice, double areaM2) {
        return areaPrice * areaM2;
    }
}
