package br.com.quality.desafio_quality.service;

import br.com.quality.desafio_quality.entity.District;
import br.com.quality.desafio_quality.repository.DistrictRepository;
import br.com.quality.desafio_quality.utils.exception.DistrictNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class DistrictService {

    private DistrictRepository districtRepository;

    @Autowired
    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public District get(long id) {
        Optional<District> districtOptional = this.districtRepository.findById(id);
        if (districtOptional.isPresent())
            return districtOptional.get();

        throw new DistrictNotFoundException("Distrito não encontrado");
    }

    public List<District> get() {
        return this.districtRepository.findAll();
    }

    public District create(District district) {
        return this.districtRepository.save(district);
    }

    public void update(long id, District d) {
        District district = this.get(id);
        district.setHouses(d.getHouses());
        district.setName(d.getName());
        district.setValueM2(d.getValueM2());
        this.districtRepository.save(district);
    }

    public void delete(long id) {
        this.districtRepository.deleteById(id);
    }

    public BigDecimal getAreaPrice(long id) {
        District district = this.get(id);
        return district.getValueM2();
    }
}
