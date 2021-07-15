package br.com.quality.desafio_quality.service;

import br.com.quality.desafio_quality.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;
}
