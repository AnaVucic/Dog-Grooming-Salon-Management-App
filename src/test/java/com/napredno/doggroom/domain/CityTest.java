package com.napredno.doggroom.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    City c;

    @BeforeEach
    void setUp() {
        c = new City();
    }

    @AfterEach
    void tearDown() {
        c = null;
    }

    @Test
    void setZipCodeOk() {
        c.setZipCode("11234");
        assertEquals("11234", c.getZipCode());
    }

    @Test
    void setZipCodeNull() { assertThrows(NullPointerException.class, () -> c.setZipCode(null)); }

    @Test
    void setZipCodeEmpty() { assertThrows(IllegalArgumentException.class, () -> c.setZipCode("")); }

    @ParameterizedTest
    @CsvSource({
            "1", "11", "111", "1111",
            "1125a", "1125 ", "1125!"
    })
    void setZipCodeIllegalArgument (String zipCode){ assertThrows(IllegalArgumentException.class, () -> c.setZipCode(zipCode)); }

    @Test
    void setNameOk() {
        c.setName("Beograd");
        assertEquals("Beograd", c.getName());
    }

    @Test
    void setNameNull(){ assertThrows(NullPointerException.class, () -> c.setName(null)); }

    @Test
    void setNameEmpty(){
        assertThrows(IllegalArgumentException.class, () -> c.setName(""));
    }

    @Test
    void setNameTooShort(){
        assertThrows(IllegalArgumentException.class, () -> c.setName("A"));
    }

    @Test
    void setNameTooLong(){
        assertThrows(IllegalArgumentException.class, ()-> c.setName("a".repeat(51)));
    }


    @Test
    void testToString() {
        c.setZipCode("11253");
        c.setName("Beograd");

        String s = c.toString();

        assertTrue(s.contains("11253"));
        assertTrue(s.contains("Beograd"));
    }

    @ParameterizedTest
    @CsvSource({
            "11000, 11000, Beograd, Beograd, true",
            "11000, 21000, Beograd, Beograd, false",
            "11000, 11000, Beograd, Novi Sad, false",
    })
    void testEquals(String zipCode1, String zipCode2,
                    String name1, String name2,
                    boolean equals) {
        c.setZipCode(zipCode1);
        c.setName(name1);

        City c2 = new City(zipCode2, name2);

        assertEquals(equals, c.equals(c2));
    }

    @Test
    void testEqualsNull() {
        assertFalse(c.equals(null));
    }

    @Test
    void testEqualsOneObject() {
        assertTrue(c.equals(c));
    }

    @Test
    void testEqualsDifferentClass() {
        assertFalse(c.equals(new Exception()));
    }

}