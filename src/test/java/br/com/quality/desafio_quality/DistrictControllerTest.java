package br.com.quality.desafio_quality;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.quality.desafio_quality.controller.DistrictController;
import br.com.quality.desafio_quality.converter.DistrictConverter;
import br.com.quality.desafio_quality.entity.District;
import br.com.quality.desafio_quality.exception.DistrictNotFoundException;
import br.com.quality.desafio_quality.form.DistrictForm;
import br.com.quality.desafio_quality.repository.DistrictRepository;
import br.com.quality.desafio_quality.repository.HouseRepository;
import br.com.quality.desafio_quality.service.DistrictService;
import br.com.quality.desafio_quality.service.HouseService;
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

@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class DistrictControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private HouseService houseService;

    @Autowired
    private DistrictService districtService;

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
    public void shouldReturnAnInstanceOfDistrictController() {
        DistrictController districtController = new DistrictController();

        Assertions.assertNotNull(districtController);
    }

    @Test
    public void shouldSaveADistrict() throws Exception {
        String payload = objectMapper.writeValueAsString(createDistrictForm());
        this.districtRepository.save(createDistrict());

        mock.perform(post("/api/districts")
                .contentType("application/json")
                .content(payload))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnAListOfAllDistricts() throws Exception{
        this.districtRepository.save(createDistrict());

        mock.perform(get("/api/districts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].name").value("Mock District"));
    }

    @Test
    public void shouldReturnASpecificDistrict() throws Exception {
        District district = this.districtRepository.save(createDistrict());

        mock.perform(get("/api/districts/{id}", district.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Mock District"));
    }

    @Test
    public void shouldReturnDistrictNotFoundExceptionForGetHouseById() throws Exception {
        mock.perform(get("/api/districts/{id}", 10L))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof DistrictNotFoundException))
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException().getMessage().contains("não encontrado")));
    }

    @Test
    public void shouldUpdateADistrict() throws Exception {
        String payload = objectMapper.writeValueAsString(createDistrictForm());
        District district = createDistrict();
        this.districtRepository.save(district);

        mock.perform(put("/api/districts/{id}", district.getId())
                .contentType("application/json")
                .content(payload))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteADistrict() throws Exception {
        District district = createDistrict();
        this.districtRepository.save(district);
        mock.perform(delete("/api/districts/{id}", district.getId()))
                .andExpect(status().isOk());
    }

    private DistrictForm createDistrictForm() {
        return new DistrictForm("Mock District", new BigDecimal(200));
    }

    private District createDistrict() {
        return DistrictConverter.districtFormToEntity(createDistrictForm());
    }
}
