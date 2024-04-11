package com.napredno.doggroom.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person p;

    @BeforeEach
    void setUp() {
        p = new Person();
    }

    @AfterEach
    void  tearDown() {
        p = null;
    }

    @Test
    void setPersonIDSveOk() {
        p.setPersonID(1L);
        assertEquals(1L,p.getPersonID());
    }

    @Test
    void setFirstnameOk() {
        p.setFirstname("Ana");
        assertEquals("Ana", p.getFirstname());
    }

    @Test
    void setFirstnameNull(){
        assertThrows(NullPointerException.class, () -> p.setFirstname(null));
    }

    @Test
    void setFirstnameEmpty(){
        assertThrows(IllegalArgumentException.class, () -> p.setFirstname(""));
    }

    @Test
    void setFirstnameTooShort(){
        assertThrows(IllegalArgumentException.class, () -> p.setFirstname("A"));
    }

    @Test
    void setFirstnameTooLong(){
        assertThrows(IllegalArgumentException.class, ()-> p.setFirstname("a".repeat(51)));
    }

    @Test
    void setLastnameOk() {
        p.setFirstname("Vucic");
        assertEquals("Vucic", p.getFirstname());
    }

    @Test
    void setLastnameNull(){
        assertThrows(NullPointerException.class, () -> p.setLastname(null));
    }

    @Test
    void setLastnameEmpty(){
        assertThrows(IllegalArgumentException.class, () -> p.setLastname(""));
    }

    @Test
    void setLastnameTooShort(){
        assertThrows(IllegalArgumentException.class, () -> p.setLastname("V"));
    }

    @Test
    void setLastnameTooLong(){
        assertThrows(IllegalArgumentException.class, ()-> p.setLastname("v".repeat(51)));
    }

    @ParameterizedTest
    @CsvSource({
            "060 123456",
            "060 000000",
            "111 111111"
    })
    @DisplayName("Test for setting a 9-digit contact number")
    void setContactNumber9DigitsOk() {
        p.setContactNumber("060 123456");
        assertEquals("060 123456", p.getContactNumber());
    }

    @ParameterizedTest
    @CsvSource({
            "060 1234567",
            "060 0000000",
            "111 1111111"
    })
    @DisplayName("Test for setting a 10-digit contact number")
    void setContactNumber10DigitsOk() {
        p.setContactNumber("060 1234567");
        assertEquals("060 1234567", p.getContactNumber());
    }

    @Test
    void setContactNumberNull(){
        assertThrows(NullPointerException.class, () -> p.setContactNumber(null));
    }

    @Test
    void setContactNumberEmpty(){ assertThrows(IllegalArgumentException.class, () -> p.setContactNumber("")); }

    @Test
    void setContactNumberInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> p.setContactNumber("060123456"));
    }


    @Test
    void testToString() {
        p.setFirstname("Ana");
        p.setLastname("Vucic");
        p.setContactNumber("111 123456");

        String s = p.toString();

        assertTrue(s.contains("Ana"));
        assertTrue(s.contains("Vucic"));
        assertTrue(s.contains("111 123456"));
        assertTrue(s.contains("0"));
    }

    @ParameterizedTest
    @CsvSource({
            "060 000000, 060 000000, true",
            "060 000000, 060 000001, false"
    })
    void testEquals(String contactNumber1, String contactNumber2, boolean equals){
        p.setContactNumber(contactNumber1);

        Person p2 = new Person();
        p2.setContactNumber(contactNumber2);

        assertEquals(equals, p.equals(p2));
    }
    @Test
    void testEqualsNull() {
        assertFalse(p.equals(null));
    }

    @Test
    void testEqualsOneObject() {
        assertTrue(p.equals(p));
    }

    @Test
    void testEqualsDifferentClass() {
        assertFalse(p.equals(new Exception()));
    }

}