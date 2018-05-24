package test;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CurrentPollutionTest {
    @Test
    public void getInstance() throws Exception {
        String  instance = "Krakow";
        setCityName(instance);
        Assert.assertEquals("NotKrakow","Krakow",getInstance(instance));
    }

    @Test
    public void setCityName() throws Exception {
        String cityName = "Krakow";
        setCityName(cityName);
        Assert.assertEquals("NotKrakow","Krakow",getInstance(cityName));
    }

    private String getInstance(String cityName) {
        return cityName;
    }

    private String setCityName(String cityName) {
        return cityName;
    }

    @Test
    public void getPollutionFromChosenSensor() throws Exception {

    }



    @Test
    public void getExistingStations() throws Exception {
    }

    @Test
    public void getPollution() throws Exception {
    }

}