package com.napredno.doggroom.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    Service s;

    @BeforeEach
    void setUp() {
        s = new Service();
    }

    @AfterEach
    void tearDown() {
        s = null;
    }

    @Test
    void setServiceIDOk() {
        s.setServiceID(1L);
        assertEquals(1L, s.getServiceID());
    }

    @Test
    void setServiceIDNull(){ assertThrows(NullPointerException.class, () -> s.setServiceID(null)); }

    @Test
    void setNameOk() {
        s.setName("Trimming");
        assertEquals("Trimming", s.getName());
    }

    @Test
    void setNameNull(){ assertThrows(NullPointerException.class, () -> s.setName(null)); }

    @Test
    void setNameEmpty(){
        assertThrows(IllegalArgumentException.class, () -> s.setName(""));
    }

    @Test
    void setNameTooShort(){
        assertThrows(IllegalArgumentException.class, () -> s.setName("T"));
    }

    @Test
    void setNameTooLong(){
        assertThrows(IllegalArgumentException.class, ()-> s.setName("t".repeat(21)));
    }

    @Test
    void setFeeOk() {
        s.setFee(new BigDecimal(1));
        assertEquals(new BigDecimal(1), s.getFee());
    }

    @Test
    void setFeeNull() { assertThrows(NullPointerException.class, () -> s.setFee(null)); }

    @ParameterizedTest
    @CsvSource({
            "-1", "0"
    })
    void setFeeTooLow(int fee) { assertThrows(IllegalArgumentException.class, () -> s.setFee(new BigDecimal(fee))); }

    @Test
    void setDurationOk() {
        s.setDuration(1);
        assertEquals(1, s.getDuration());
    }

    @ParameterizedTest
    @CsvSource({
            "-1", "0"
    })
    void setDurationTooLow(int duration) { assertThrows(IllegalArgumentException.class, () -> s.setDuration(duration)); }

    @Test
    void testToString() {
        s.setServiceID(23L);
        s.setName("Bathing");
        s.setFee(new BigDecimal(45));
        s.setDuration(67);

        String str = s.toString();

        assertTrue(str.contains("23"));
        assertTrue(str.contains("Bathing"));
        assertTrue(str.contains("45"));
        assertTrue(str.contains("67"));
    }

}