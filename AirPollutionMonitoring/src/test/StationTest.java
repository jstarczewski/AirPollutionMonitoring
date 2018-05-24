package test;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StationTest {
    @Test
    public void setStationName(String stationName) throws Exception {
        String stationName1 = "testWarszawa";
        setStationName(stationName1);
        Assert.assertEquals("notWarszawa","testWarszawa",getStationName(stationName1));
    }

    @Test
    public void getStationId() throws Exception {
        int id = 1;
        Assert.assertEquals("2","1",getStationId(id));
    }

    private int getStationId(int id) {
        return id;
    }

    @Test
    public void getStationName() throws Exception {
        String stationName = "Warszawa";
        setStationName(stationName);
        Assert.assertEquals("notWarszawa","noWarszawa",getStationName(stationName));
    }

    private String getStationName(String stationName) {
        return stationName;
    }

}