package com.napredno.doggroom.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BreedTest {

    Breed b;

    @BeforeEach
    void setUp(){
        b = new Breed();
    }

    @AfterEach
    void tearDown() {
        b = null;
    }

    @Test
    void setBreedIDOk() {
        b.setBreedID(1L);
        assertEquals(1L, b.getBreedID());
    }

    @Test
    void setBreedIDNull() {
        assertThrows(NullPointerException.class, () -> b.setBreedID(null));
    }

    @Test
    void setNameOk() {
        b.setName("Westie");
        assertEquals("Westie", b.getName());
    }

    @Test
    void setNameNull(){ assertThrows(NullPointerException.class, () -> b.setName(null)); }

    @Test
    void setNameEmpty(){
        assertThrows(IllegalArgumentException.class, () -> b.setName(""));
    }

    @Test
    void setNameTooShort(){
        assertThrows(IllegalArgumentException.class, () -> b.setName("ww"));
    }

    @Test
    void setNameTooLong(){
        assertThrows(IllegalArgumentException.class, ()-> b.setName("w".repeat(31)));
    }

    @Test
    void testToString() {
        b.setName("Westie");

        String s = b.toString();

        assertTrue(s.contains("Westie"));
    }

    @ParameterizedTest
    @CsvSource({
            "Westie, Westie, true",
            "Westie, Husky, false"
    })
    void testEquals(String name1, String name2, boolean equals){
        b.setName(name1);
        b.setBreedID(1L);

        Breed b2 = new Breed();
        b2.setName(name2);
        b2.setBreedID(1L);

        assertEquals(equals, b.equals(b2));
    }

    @Test
    void testEqualsNull() {
        assertFalse(b.equals(null));
    }

    @Test
    void testEqualsOneObject() {
        assertTrue(b.equals(b));
    }

    @Test
    void testEqualsDifferentClass() {
        assertFalse(b.equals(new Exception()));
    }

}