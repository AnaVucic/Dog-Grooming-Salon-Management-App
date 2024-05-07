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
        Dog dog = new Dog(
                1L,
                new Person(
                        1L,
                        "Ana",
                        "Vucic",
                        "060 000000"),
                new Breed(1L, "Westie"),
                "Meda");
        Salon salon = new Salon(
                1L,
                "Beogradska 23",
                new City("11000", "Beograd"));
        a.setAppointmentID(9L);
        a.setDog(dog);
        a.setSalon(salon);
        a.setTotalFee(new BigDecimal(23));
        a.setTotalDuration(45);
        a.setDateTime(LocalDateTime.now().plusHours(25));

        String s = a.toString();

        assertTrue(s.contains("appointmentID=9"));
        assertTrue(s.contains("dateTime="+a.getDateTime()));
        assertTrue(s.contains("dog="+a.getDog().toString()));
        assertTrue(s.contains("salon="+a.getSalon().toString()));
        assertTrue(s.contains("totalFee=23"));
        assertTrue(s.contains("totalDuration=45"));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 1, 1, 0, 0, 23, 23, true",
            "1, 2, 1, 1, 25, 25, 23, 23, false",
            "1, 1, 1, 2, 25, 25, 23, 23, false",
            "1, 1, 1, 1, 25, 26, 23, 23, false",
            "1, 1, 1, 1, 25, 25, 23, 24, false"

    })
    void testEquals(int totalDuration1, int totalDuration2,
                    Long id1, Long id2,
                    int plusHours1, int plusHours2,
                    BigDecimal totalFee1, BigDecimal totalFee2,
                    boolean equals ) {
        Dog d = new Dog();
        d.setDogID(1L);
        d.setName("Meda");
        Salon salon = new Salon("Beogradska 23" , new City("11000", "Beograd"));
        Service s = new Service(1L, "Trimming", new BigDecimal(23), 45);

        a.setTotalDuration(totalDuration1);
        a.setAppointmentID(id1);
        a.setDateTime(LocalDateTime.of(2025, 1, 1, 0, 0).plusHours(plusHours1));
        a.setTotalFee(totalFee1);
        a.setDog(d);
        a.setSalon(salon);
        a.setServices(List.of(s));

        Appointment a2 = new Appointment();
        a2.setTotalDuration(totalDuration2);
        a2.setAppointmentID(id2);
        a2.setDateTime(LocalDateTime.of(2025, 1, 1, 0, 0).plusHours(plusHours2));
        a2.setTotalFee(totalFee2);
        a2.setDog(d);
        a2.setSalon(salon);
        a2.setServices(List.of(s));

        assertEquals(equals, a.equals(a2));
    }

    @Test
    void testEqualsNull() {
        assertFalse(a.equals(null));
    }

    @Test
    void testEqualsOneObject() {
        assertTrue(a.equals(a));
    }

    @Test
    void testEqualsDifferentClass() {
        assertFalse(a.equals(new Exception()));
    }
}