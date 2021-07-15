package br.com.quality.desafio_quality.service;

import br.com.quality.desafio_quality.entity.District;
import br.com.quality.desafio_quality.repository.DistrictRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@NoArgsConstructor
public class DistrictService {

    private DistrictRepository districtRepository;

    @Autowired
    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public District get(long id) {
        return this.districtRepository.getById(id);
    }

    public List<District> get() {
        return this.districtRepository.findAll();
    }

    public District create(District district) {
        return this.districtRepository.save(district);
    }

    public void delete(District district) {
        this.districtRepository.delete(district);
    }

    public BigDecimal getAreaPrice(long id) {
        return this.districtRepository.getById(id).getValueM2();
    }
}
