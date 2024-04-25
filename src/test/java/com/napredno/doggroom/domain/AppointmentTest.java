package com.napredno.doggroom.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    Appointment a;

    @BeforeEach
    void setUp() {
        a = new Appointment();
    }

    @AfterEach
    void tearDown() {
        a = null;
    }

    @Test
    void setAppointmentIDOk() {
        a.setAppointmentID(1L);
        assertEquals(1L, a.getAppointmentID());
    }

    @Test
    void setAppointmentIDNull() { assertThrows(NullPointerException.class, () -> a.setAppointmentID(null)); }

    @Test
    void setDateTimeOk() {
        a.setDateTime(LocalDateTime.MAX);
        assertEquals(LocalDateTime.MAX, a.getDateTime());
    }

    @Test
    void setDateTimeNull() { assertThrows(NullPointerException.class, () -> a.setDateTime(null)); }

    @Test
    void setDateTimeInvalid() { assertThrows(IllegalArgumentException.class, () -> a.setDateTime(LocalDateTime.now())); }

    @Test
    void setDogOk() {
        Dog d = new Dog();
        d.setDogID(1L);
        d.setName("Meda");
        a.setDog(d);

        assertEquals(d, a.getDog());
    }

    @Test
    void setDogNull() { assertThrows(NullPointerException.class, () -> a.setDog(null)); }

    @Test
    void setSalonOk() {
        City c = new City("11000", "Beograd");
        Salon s = new Salon(1L, "Beogradska 23", c);
        a.setSalon(s);

        assertEquals(s, a.getSalon());
    }

    @Test
    void setSalonNull() { assertThrows(NullPointerException.class, () -> a.setSalon(null)); }

    @Test
    void setTotalFeeOk() {
        a.setTotalFee(new BigDecimal(1));
        assertEquals(new BigDecimal(1), a.getTotalFee());
    }

    @Test
    void setTotalFeeNull() { assertThrows(NullPointerException.class, () -> a.setTotalFee(null)); }

    @ParameterizedTest
    @CsvSource({
            "-1", "0"
    })
    void setTotalFeeTooLow(int totalFee) { assertThrows(IllegalArgumentException.class, () -> a.setTotalFee(new BigDecimal(totalFee))); }

    @Test
    void setTotalDurationOk() {
        a.setTotalDuration(1);
        assertEquals(1, a.getTotalDuration());
    }

    @ParameterizedTest
    @CsvSource({
            "-1", "0"
    })
    void setTotalDurationTooLow(int totalDuration) { assertThrows(IllegalArgumentException.class, () -> a.setTotalDuration(totalDuration)); }


    @Test
    void setServicesOk() {
        Service s = new Service(1L, "Trimming", new BigDecimal(20), 20);
        List<Service> serviceList = new ArrayList<>();
        serviceList.add(s);
        a.setServices(serviceList);

        assertEquals(serviceList, a.getServices());
    }

    @Test
    void setServicesNull() { assertThrows(NullPointerException.class, () -> a.setServices(null)); }

    @Test
    void setServicesEmpty() {
        List<Service> serviceList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> a.setServices(serviceList));
    }

    @Test
    void testToString() {
    }

    @Test
    void testEquals() {
    }
}