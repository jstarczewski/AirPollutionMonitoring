import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

public class CurrentPollutionTest {

    private CurrentPollution pollution;

    @Before
    public void setUp() {
        pollution = CurrentPollution.getInstance();
    }

    @Test
    public void setCityName() {
        String name = "Name";
        pollution.setCityName(name);
        assertEquals(pollution.getCityName(), name);
    }

}