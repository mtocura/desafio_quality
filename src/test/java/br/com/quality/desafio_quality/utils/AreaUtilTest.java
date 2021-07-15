package br.com.quality.desafio_quality.utils;

import br.com.quality.desafio_quality.dto.RoomDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class AreaUtilTest {

    private List<Double> subjectsMock;

    private RoomDTO objectMock;

    @BeforeEach
    public void init() {
        RoomDTO r1 = new RoomDTO(1l, "Mock 1", 10, 10);
        RoomDTO r2 = new RoomDTO(2l, "Mock 2", 10, 10);
        RoomDTO r3 = new RoomDTO(3l, "Mock 3", 10, 10);

        double a1 = AreaUtil.calculate(r1.getWidth(), r1.getLength());
        double a2 = AreaUtil.calculate(r2.getWidth(), r2.getLength());
        double a3 = AreaUtil.calculate(r3.getWidth(), r3.getLength());

        this.objectMock = r1;
        this.subjectsMock = new ArrayList(Arrays.asList(a1, a2, a3));
    }


    @Test
    public void shouldReturnCalculateAreaRoom(){
        double expectedAreaRoom = (double) 10 * 10;

        double responseAreaRoom = AreaUtil.calculate(this.objectMock.getWidth(), this.objectMock.getLength());

        Assertions.assertEquals(expectedAreaRoom, responseAreaRoom);

    }

    @Test
    public void shouldReturnCalculateTotalArea(){
        double expectedArea = (double) 100 + 100 + 100;

        double responseArea = AreaUtil.calculateTotalArea(this.subjectsMock);

        Assertions.assertEquals(expectedArea, responseArea);

    }
}
