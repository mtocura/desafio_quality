package br.com.quality.desafio_quality.service;

import br.com.quality.desafio_quality.entity.House;
import br.com.quality.desafio_quality.repository.HouseRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class HouseService {

    private HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public House get(long id) {
        return this.houseRepository.getById(id);
    }

    public List<House> get() {
        return this.houseRepository.findAll();
    }

    public House create(House house) {
        return this.houseRepository.save(house);
    }

    public void delete(House house) {
        this.houseRepository.delete(house);
    }
}
