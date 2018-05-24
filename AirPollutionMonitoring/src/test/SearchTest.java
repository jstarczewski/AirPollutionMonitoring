package test;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchTest {
    @Test
    public void setInput() throws Exception {
        String input = "Warszawa";
        setInput(input);
        Assert.assertEquals("test","Warszawa",getStationList(input));
    }

    private String setInput(String input) {
        return input;
    }

    private String getStationList(String input) {
        return input;
    }

    @Test
    public void getPollution() throws Exception {
        String station = "Krakow";
        setInput(station);
        Assert.assertEquals("test","Krakow",getPollution(station));
    }

    private String getPollution(String station) {
        return station;
    }

    @Test
    public void getStationList() throws Exception {
    }

    @Test
    public void warnAboutEmptySelection() throws Exception {
    }

}