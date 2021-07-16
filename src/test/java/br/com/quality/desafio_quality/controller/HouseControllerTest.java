package br.com.quality.desafio_quality.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.quality.desafio_quality.converter.DistrictConverter;
import br.com.quality.desafio_quality.converter.HouseConverter;
import br.com.quality.desafio_quality.entity.District;
import br.com.quality.desafio_quality.entity.House;
import br.com.quality.desafio_quality.exception.HouseNotFoundException;
import br.com.quality.desafio_quality.form.*;
import br.com.quality.desafio_quality.repository.DistrictRepository;
import br.com.quality.desafio_quality.repository.HouseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class HouseControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @BeforeEach
    public void init() {
        this.districtRepository.deleteAll();
        this.houseRepository.deleteAll();
    }

    @Test
    public void shouldReturnAnInstanceOfHouseController() {
        HouseController houseController = new HouseController();

        Assertions.assertNotNull(houseController);
    }

    @Test
    public void shouldSaveAHouse() throws Exception {
        District district = createDistrict();
        this.districtRepository.save(district);
        HouseForm houseForm = createHouseForm();
        houseForm.setDistrictId(district.getId());
        String payload = objectMapper.writeValueAsString(houseForm);
        this.houseRepository.save(createHouse());

        mock.perform(post("/api/houses")
                .contentType("application/json")
                .content(payload))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnAListOfAllHouses() throws Exception{
        this.houseRepository.save(createHouse());

        mock.perform(get("/api/houses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].name").value("Mock House"));
    }

    @Test
    public void shouldReturnASpecificHouse() throws Exception {
        House house = this.houseRepository.save(createHouse());

        mock.perform(get("/api/houses/{id}", house.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Mock House"))
                .andExpect(jsonPath("$.rooms").exists())
                .andExpect(jsonPath("$.rooms").isNotEmpty());
    }

    @Test
    public void shouldReturnHouseNotFoundExceptionForGetHouseById() throws Exception {
        mock.perform(get("/api/houses/{id}", 10L))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof HouseNotFoundException))
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException().getMessage().contains(HouseNotFoundException.DEFAULT_MESSAGE)));
    }

    @Test
    public void shouldUpdateAHouse() throws Exception {
        this.districtRepository.save(createDistrict());
        House house = createHouse();
        this.houseRepository.save(house);
        String payload = objectMapper.writeValueAsString(createHouseUpdateForm());

        mock.perform(put("/api/houses/{id}", house.getId())
                .contentType("application/json")
                .content(payload))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteAHouse() throws Exception {
        House house = createHouse();
        this.houseRepository.save(house);
        mock.perform(delete("/api/houses/{id}", house.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void getCalculateAreaShouldThrowOk() throws Exception {
        House house = this.houseRepository.save(createHouse());

        mock.perform(get("/api/houses/{id}/areas", house.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void getCalculatePriceShouldThrowOk() throws Exception {
        District district = createDistrict();
        this.districtRepository.save(district);
        House houseEntity = createHouse();
        houseEntity.setDistrictId(district.getId());
        House house = this.houseRepository.save(houseEntity);

        mock.perform(get("/api/houses/{id}/price", house.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void getRoomsAreasShouldThrowOk() throws Exception {
        this.districtRepository.save(createDistrict());
        House house = this.houseRepository.save(createHouse());

        mock.perform(get("/api/houses/{id}/areas/rooms", house.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void getCalculateAreaRoomShouldThrowOk() throws Exception {
        this.districtRepository.save(createDistrict());
        House house = this.houseRepository.save(createHouse());

        mock.perform(get("/api/houses/{id}/rooms/area", house.getId()))
                .andExpect(status().isOk());
    }


    private HouseForm createHouseForm() {
        return new HouseForm("Mock House", 1L, createRoomFormList());
    }

    private HouseUpdateForm createHouseUpdateForm() {
        return new HouseUpdateForm("Mock House", 1L, createRoomUpdateFormList());
    }

    private DistrictForm createDistrictForm() {
        return new DistrictForm("Mock District", new BigDecimal(200));
    }

    private House createHouse() {
        return HouseConverter.houseFormToEntity(createHouseForm());
    }

    private District createDistrict() {
        return DistrictConverter.districtFormToEntity(createDistrictForm());
    }

    private List<RoomForm> createRoomFormList() {
        RoomForm roomForm1 = new RoomForm("Cozinha", 6, 8);
        RoomForm roomForm2 = new RoomForm("Sala", 20, 12);

        return new ArrayList<>(Arrays.asList(roomForm1, roomForm2));
    }

    private List<RoomUpdateForm> createRoomUpdateFormList() {
        RoomUpdateForm roomUpdateForm1 = new RoomUpdateForm(1L,"Cozinha", 6, 8);
        RoomUpdateForm roomUpdateForm2 = new RoomUpdateForm(2L, "Sala", 20, 22);

        return new ArrayList<>(Arrays.asList(roomUpdateForm1, roomUpdateForm2));
    }
}
