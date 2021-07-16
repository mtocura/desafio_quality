package br.com.quality.desafio_quality.service;

import br.com.quality.desafio_quality.dto.HouseSizeDTO;
import br.com.quality.desafio_quality.dto.HouseValueDTO;
import br.com.quality.desafio_quality.dto.RoomSizeDTO;
import br.com.quality.desafio_quality.entity.District;
import br.com.quality.desafio_quality.entity.House;
import br.com.quality.desafio_quality.entity.Room;
import br.com.quality.desafio_quality.exception.HouseNotFoundException;
import br.com.quality.desafio_quality.repository.HouseRepository;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
public class HouseServiceTest {

    private HouseRepository houseRepository;
    private HouseService houseService;
    private DistrictService districtService;
    private House houseMock;
    private District districtMock;
    private List<Room> roomsMock;

    @BeforeEach
    public void init() {
        this.houseRepository = Mockito.mock(HouseRepository.class);
        this.districtService = Mockito.mock(DistrictService.class);
        this.houseService = new HouseService(this.houseRepository, this.districtService);
        this.roomsMock = Arrays.asList(
                new Room("Sala", 20L, 20L),
                new Room("Cozinha", 10L, 10L)
        );
        this.houseMock = new House("Casa 1", 1L, this.roomsMock);
        this.districtMock = new District( "Bairro", new BigDecimal(10));
    }

    @Test
    public void shouldReturnAnInstanceOfHouseService() {
        HouseService houseService = new HouseService();

        Assertions.assertNotNull(houseService);
    }

    @Test
    public void shouldReturnAHouseWhenPassingCorrectId() {
        Optional<House> optionalHouse = Optional.of(this.houseMock);
        Mockito.when(this.houseRepository.findById(1L)).thenReturn(optionalHouse);

        House expectedHouse = this.houseMock;
        House responseHouse = this.houseService.get(1L);

        assertThat(responseHouse).usingRecursiveComparison().isEqualTo(expectedHouse);
    }

    @Test
    public void shouldReturnExceptionWhenPassingInexistentIdHouse() {
        Optional<House> optionalHouse = Optional.empty();
        Mockito.when(this.houseRepository.findById(1L)).thenReturn(optionalHouse);

        RuntimeException expectedException = new HouseNotFoundException("Propriedade não encontrada.");
        RuntimeException responseException = Assertions.assertThrows(
                HouseNotFoundException.class,
                () -> this.houseService.get(1L)
        );

        assertThat(responseException).usingRecursiveComparison().isEqualTo(expectedException);
    }

    @Test
    public void shouldReturnAListOfHouse() {
        List<House> expectedHouseList = Arrays.asList(this.houseMock);
        Mockito.when(this.houseRepository.findAll()).thenReturn(expectedHouseList);

        List<House> responseHouseList = this.houseService.get();

        assertThat(responseHouseList).usingRecursiveComparison().isEqualTo(expectedHouseList);
    }

    @Test
    public void shouldSaveAHouse() {
        Mockito.when(this.houseRepository.save(this.houseMock)).thenReturn(this.houseMock);
        Mockito.when(this.districtService.get(this.houseMock.getDistrictId())).thenReturn(this.districtMock);

        this.houseService.create(this.houseMock);

        Mockito.verify(this.houseRepository).save(this.houseMock);
    }

    @Test
    public void shouldUpdateAHouse() {
        Mockito.when(this.houseRepository.save(this.houseMock)).thenReturn(this.houseMock);
        Mockito.when(this.houseRepository.findById(1L)).thenReturn(Optional.of(this.houseMock));

        this.houseService.update(1L, this.houseMock);

        Mockito.verify(this.houseRepository).save(this.houseMock);
    }

    @Test
    public void shouldDeleteAHouse() {
        Mockito.doNothing().when(this.houseRepository).deleteById(1L);

        this.houseService.delete(1L);

        Mockito.verify(this.houseRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void shouldCalculateHouseAreaAndReturnHouseSizeDTO() {
        HouseSizeDTO expectedHouseSizeDTO = new HouseSizeDTO(this.houseMock.getName(), 500L);
        Mockito.when(this.houseRepository.findById(1L)).thenReturn(Optional.of(this.houseMock));

        HouseSizeDTO responseHouseArea = this.houseService.getHouseArea(1L);

        assertThat(responseHouseArea).usingRecursiveComparison().isEqualTo(expectedHouseSizeDTO);
    }

    @Test
    public void shouldCalculateHousePriceAndReturnHouseValueDTO() {
        Double houseArea = this.houseMock.getRooms()
                .stream()
                .map(room -> room.getLength() * room.getWidth())
                .reduce(Double::sum)
                .get();
        BigDecimal housePrice = this.districtMock.getValueM2().multiply(new BigDecimal(houseArea));
        HouseValueDTO expectedHouseValueDTO = new HouseValueDTO(this.houseMock.getName(), houseArea, housePrice.setScale(2, RoundingMode.FLOOR));
        Mockito.when(this.houseRepository.findById(1L)).thenReturn(Optional.of(this.houseMock));
        Mockito.when(this.districtService.get(this.houseMock.getDistrictId())).thenReturn(this.districtMock);

        HouseValueDTO responseHouseValueDTO = this.houseService.getHousePrice(1L);

        assertThat(responseHouseValueDTO).usingRecursiveComparison().isEqualTo(expectedHouseValueDTO);
    }

    @Test
    public void shouldReturnListOfRoomsAreasOfAHouse() {
        List<RoomSizeDTO> expectedListRoomSizeDTO = this.houseMock.getRooms()
                .stream()
                .map(room -> new RoomSizeDTO(room.getName(), room.getLength() * room.getWidth()))
                .collect(Collectors.toList());
        Mockito.when(this.houseRepository.findById(1L)).thenReturn(Optional.of(this.houseMock));

        List<RoomSizeDTO> responseListRoomSizeDTO = this.houseService.getRoomsAreas(1L);

        assertThat(responseListRoomSizeDTO).usingRecursiveComparison().isEqualTo(expectedListRoomSizeDTO);
    }

    @Test
    public void shouldCalculateLargestRoomOfAHouse() {
        Room largestRoom = this.roomsMock.get(0);
        RoomSizeDTO expectedLargestRoom = new RoomSizeDTO(largestRoom.getName(), largestRoom.getLength() * largestRoom.getWidth());
        Mockito.when(this.houseRepository.findById(1L)).thenReturn(Optional.of(this.houseMock));

        RoomSizeDTO responseLargestRoom = this.houseService.largestRoom(1L);

        assertThat(responseLargestRoom).usingRecursiveComparison().isEqualTo(expectedLargestRoom);
    }

}
