package test;

import org.junit.Assert;
import org.junit.Test;
import sun.management.Sensor;

import static org.junit.Assert.*;

public class SensorTest {
    @Test
    public void getId() throws Exception {
        final int id = 1;
        final Sensor sensor = new Sensor();
        sensor.getId();
        Assert.assertArrayEquals(sensor.getId(), id);
    }

    @Test
    public void setName() throws Exception {
        final String name = "Warszawa";
        final Sensor sensor = new Sensor();
        sensor.setName("Warszawa");
        Assert.assertEquals(sensor.getClass(), name);
    }

    @Test
    public void setValue() throws Exception {
        final double values = 1;
        final double values1 = 1;
        final Sensor sensor = new Sensor();
        sensor.setValue(1);
        Assert.assertArrayEquals(sensor.getClass(), values1);
    }

    @Test
    public void setTimestamp() throws Exception {
    }

    @Test
    public void toString() throws Exception {
    }

}