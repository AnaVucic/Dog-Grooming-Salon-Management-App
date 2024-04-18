package com.napredno.doggroom.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {

    Dog d;

    @BeforeEach
    void setUp() {
        d = new Dog();
    }

    @AfterEach
    void tearDown() {
        d = null;
    }

    @Test
    void setDogIDOk() {
        d.setDogID(1L);
        assertEquals(1L, d.getDogID());
    }

    @Test
    void setDogIDNull(){
        assertThrows(NullPointerException.class, () -> d.setDogID(null));
    }

    @Test
    void setPersonOk() {
        d.setPerson(new Person("Ana", "Vucic", "060 123456"));
        assertEquals(new Person("Ana", "Vucic", "060 123456"), d.getPerson());
    }

    @Test
    void setPersonNull() { assertThrows(NullPointerException.class, () -> d.setPerson(null)); }

    @Test
    void setBreedOk() {
        d.setBreed(new Breed(1L,"Husky"));
        assertEquals(new Breed(1L,"Husky"), d.getBreed());
    }

    @Test
    void setBreedNull(){
        assertThrows(NullPointerException.class, () -> d.setBreed(null));
    }

    @Test
    void setNameOk() {
    d.setName("Meda");
    assertEquals("Meda", d.getName());
    }

    @Test
    void setNameNull(){ assertThrows(NullPointerException.class, () -> d.setName(null)); }

    @Test
    void setNameEmpty(){
        assertThrows(IllegalArgumentException.class, () -> d.setName(""));
    }

    @Test
    void setNameTooShort(){
        assertThrows(IllegalArgumentException.class, () -> d.setName("m"));
    }

    @Test
    void setNameTooLong(){
        assertThrows(IllegalArgumentException.class, ()-> d.setName("m".repeat(51)));
    }

    @Test
    void testToString() {
        d.setName("Meda");
        d.setDogID(1L);

        String s = d.toString();

        assertTrue(s.contains("Meda"));
        assertTrue(s.contains("1"));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, Meda, Meda, true",
            "1, 2, Meda, Meda, false",
            "1, 1, Meda, Leo, false"
    })
    void testEquals(Long ID1,
                    Long ID2,
                    String name1,
                    String name2,
                    boolean equals){
        d.setDogID(ID1);
        d.setName(name1);

        Dog d2 = new Dog();
        d2.setDogID(ID2);
        d2.setName(name2);

        assertEquals(equals, d.equals(d2));
    }

    @Test
    void testEqualsNull() {
        assertFalse(d.equals(null));
    }

    @Test
    void testEqualsOneObject() {
        assertTrue(d.equals(d));
    }

    @Test
    void testEqualsDifferentClass() {
        assertFalse(d.equals(new Exception()));
    }
}