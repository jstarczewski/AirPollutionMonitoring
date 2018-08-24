import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;
public class StationTest {

    private Station stationError;
    private Station stationNotError;

    @Before
    public void setUp() {

        stationError = new Station(1);
        // "id":550,"stationName":"Warszawa-Ursynów"
        stationNotError = new Station("\"id\":550,\"stationName\":\"Warszawa-Ursynów\"");
    }

    @Test
    public void extractStationId() {
        assertEquals(550, stationNotError.extractStationId("\"id\":550,\"stationName\":\"Warszawa-Ursynów\""));
    }

    @Test
    public void extractStationName() {
        assertEquals("Warszawa-Ursynów", stationNotError.extractStationName("\"id\":550,\"stationName\":\"Warszawa-Ursynów\""));
    }

    @Test
    public void getStationName() {
        assertEquals("Stacja o podanej nazwie nie istnieje, nie udało się odnaleźć stacji o podobnej nazwie", stationError.getStationName());
    }
}