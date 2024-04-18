package com.napredno.doggroom.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SalonTest {

    Salon s;

    @BeforeEach
    void setUp() {
        s = new Salon();
    }

    @AfterEach
    void tearDown() {
        s = null;
    }

    @Test
    void setSalonIDOk() {
        s.setSalonID(1L);
        assertEquals(1L, s.getSalonID());
    }

    @Test
    void setSalonIDNull() { assertThrows(NullPointerException.class, () -> s.setSalonID(null)); }

    @Test
    void setAddressOk() {
        s.setAddress("Beogradska 23");
        assertEquals("Beogradska 23", s.getAddress());
    }

    @Test
    void setAddressNull() { assertThrows(NullPointerException.class, () -> s.setAddress(null)); }

    @Test
    void setAddressEmpty(){
        assertThrows(IllegalArgumentException.class, () -> s.setAddress(""));
    }

    @ParameterizedTest
    @CsvSource({
            "B", "Be", "Beo", "Beog"
    })
    void setAddressTooShort(String address){
        assertThrows(IllegalArgumentException.class, () -> s.setAddress(address));
    }

    @Test
    void setAddressTooLong(){
        assertThrows(IllegalArgumentException.class, ()-> s.setAddress("a".repeat(51)));
    }

    @Test
    void setCityOk() {
        City city1 = new City("11000", "Beograd");
        s.setCity(city1);
        assertEquals(city1, s.getCity());
    }

    @Test
    void setCityNull(){ assertThrows(NullPointerException.class, () -> s.setCity(null)); }


    @Test
    void testToString() {
        s.setSalonID(12L);
        s.setAddress("Visnjiceva 34");
        City city1 = new City("11000", "Beograd");
        s.setCity(city1);

        String str = s.toString();

        assertTrue(str.contains("12"));
        assertTrue(str.contains("Visnjiceva 34"));
        assertTrue(str.contains("11000"));
        assertTrue(str.contains("Beograd"));
    }

}