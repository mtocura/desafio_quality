package br.com.quality.desafio_quality.service;

import br.com.quality.desafio_quality.entity.District;
import br.com.quality.desafio_quality.repository.DistrictRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DistrictServiceTest {
    private DistrictService service;
    private DistrictRepository repository;

    @BeforeEach
    void init() {
        repository = Mockito.mock(DistrictRepository.class);
        service = new DistrictService(repository);
    }

    @Test
    void shouldReturnValidDistrict() {
        // Arrange
        District expected = Factory.newValidDistrict();
        Optional<District> opt = Optional.of(expected);
        when(this.repository.findById(0L)).thenReturn(opt);

        // Act
        District d = this.service.get(0L);

        // Assert
        verify(this.repository, times(1)).findById(0L);
        assertEquals(d, expected);
    }

    @Test
    void shouldReturnListOfValidDistricts() {
        // Arrange
        List<District> expected = Factory.newValidDistrictsList();
        when(this.repository.findAll()).thenReturn(expected);

        // Act
        List<District> list = this.service.get();

        // Assert
        verify(this.repository, times(1)).findAll();
        assertEquals(list, expected);
    }

    @Test
    void shouldCreateDistrict() {
        // Arrange
        District expected = Factory.newValidDistrict();
        when(this.repository.save(expected)).thenReturn(expected);

        // Act
        District d = this.service.create(expected);

        // Assert
        verify(this.repository, times(1)).save(expected);
        assertEquals(d, expected);
    }

    @Test
    void shouldUpdateDistrict() {
        // Arrange
        District expected = Factory.newValidDistrict();
        Optional<District> opt = Optional.of(expected);

        when(this.repository.save(expected)).thenReturn(expected);
        when(this.repository.findById(0L)).thenReturn(opt);

        // Act
        this.service.update(0L, expected);

        // Assert
        verify(this.repository, times(1)).save(expected);
        verify(this.repository, times(1)).findById(0L);
    }

    @Test
    void shouldDeleteDistrict() {
        // Arrange
        doNothing().when(this.repository).deleteById(0L);

        // Act
        this.service.delete(0L);

        // Assert
        verify(this.repository, times(1)).deleteById(0L);
    }

    @Test
    void shouldReturnAreaPrice() {
        // Arrange
        District district = Factory.newValidDistrict();
        Optional<District> opt = Optional.of(district);
        when(this.repository.findById(0L)).thenReturn(opt);

        // Act
        BigDecimal price = this.service.getAreaPrice(0L);

        // Assert
        verify(this.repository, times(1)).findById(0L);
        assertEquals(price, district.getValueM2());
    }
}
