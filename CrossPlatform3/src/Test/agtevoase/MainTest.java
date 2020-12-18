package agtevoase;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void isValidEmailAddress() {
        assertTrue(Utils.isValidEmailAddress("a@b.com"));
        assertFalse(Utils.isValidEmailAddress("..@b.com"));
        assertTrue(Utils.isValidEmailAddress("?!?@b.com"));
        assertTrue(Utils.isValidEmailAddress("зааза@b.com"));
        assertTrue(Utils.isValidEmailAddress("hi@localhost"));
        assertFalse(Utils.isValidEmailAddress("@"));
    }


}