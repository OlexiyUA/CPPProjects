package agtevoase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void splitIntoSentences() {
        String src = "Hello, world. I am nonentity.";
        String[] splitted = {"Hello, world. ", "I am nonentity."};
        String[] returned = Utils.splitIntoSentences(src);
        assertArrayEquals(splitted, returned);
    }

    @Test
    void removeConsonantsOfGivenLength() {
        String src = "Wahah ghggsds ayaay ayya heheh.";
        assertEquals(" ghggsds ayaay ayya .",
                Utils.removeConsonantsOfGivenLength(src, 5));
    }
}