package br.com.quality.desafio_quality.service;

import br.com.quality.desafio_quality.entity.District;
import br.com.quality.desafio_quality.entity.Room;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

final class Factory {
    private Factory() {}

    public static District newValidDistrict() {
        return new District("District 9", BigDecimal.valueOf(100L));
    }

    public static List<District> newValidDistrictsList() {
        return Arrays.asList(newValidDistrict());
    }

    public static Room newValidRoom() {
        return new Room("Sala 1408", 10.0, 10.0);
    }

    public static List<Room> newValidRoomsList() {
        return Arrays.asList(newValidRoom());
    }
}
