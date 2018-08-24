import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SearchTest {

    public Search search;
    @Before
    public void setUp() {
        search = new Search();
    }

    @Test
    public void setInput() {
        search.setInput("Warszawa");
        assertEquals("Warszawa", search.getInput());
    }

    @Test
    public void getPollution() {
        // composition of CurrentPollution singleton class no need to test it here
    }

    @Test
    public void getStationList() {
        search.setInput("");
        assertEquals("Nie wpisano nazwy lokacji", search.getStationList().get(0).getStationName());
    }

    @Test
    public void warnAboutEmptySelection() {

    }

    @Test
    public void isCityNameValid() {
        assertTrue(search.isCityNameValid("Warszawa"));
        assertFalse(search.isCityNameValid("War213Szawa"));
        assertFalse(search.isCityNameValid(""));
    }
}