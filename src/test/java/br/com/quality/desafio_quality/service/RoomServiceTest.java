package br.com.quality.desafio_quality.service;

import br.com.quality.desafio_quality.entity.Room;
import br.com.quality.desafio_quality.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RoomServiceTest {
    private RoomService service;
    private RoomRepository repository;

    @BeforeEach
    void init() {
        repository = Mockito.mock(RoomRepository.class);
        service = new RoomService(repository);
    }

    @Test
    void shouldReturnValidRoom() {
        // Arrange
        Room expected = Factory.newValidRoom();
        Optional<Room> opt = Optional.of(expected);
        when(this.repository.findById(0L)).thenReturn(opt);

        // Act
        Room r = this.service.get(0L);

        // Assert
        verify(this.repository, times(1)).findById(0L);
        assertEquals(r, expected);
    }

    @Test
    void shouldReturnListOfValidRooms() {
        // Arrange
        List<Room> expected = Factory.newValidRoomsList();
        when(this.repository.findAll()).thenReturn(expected);

        // Act
        List<Room> list = this.service.get();

        // Assert
        verify(this.repository, times(1)).findAll();
        assertEquals(list, expected);
    }

    @Test
    void shouldCreateRoom() {
        // Arrange
        Room expected = Factory.newValidRoom();
        when(this.repository.save(expected)).thenReturn(expected);

        // Act
        Room r = this.service.create(expected);

        // Assert
        verify(this.repository, times(1)).save(expected);
        assertEquals(r, expected);
    }

    @Test
    void shouldDeleteRoom() {
        // Arrange
        doNothing().when(this.repository).deleteById(0L);

        // Act
        this.service.delete(0L);

        // Assert
        verify(this.repository, times(1)).deleteById(0L);
    }

    @Test
    void shouldCalculateCorrect() {
        // Arrange
        double w = 10.0;
        double h = 10.0;
        double expected = w * h;

        // Act
        double area = this.service.calculateArea(w, h);

        // Assert
        assertEquals(expected, area);
    }

    @Test
    void shouldReturnCorrectPrice() {
        // Arrange
        double area = 100.0;
        double price = 100.0;
        double expected = area * price;

        // Act
        double result = this.service.calculatePrice(price, area);

        // Assert
        assertEquals(expected, result);
    }
}
