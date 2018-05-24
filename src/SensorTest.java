import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SensorTest {
    private Sensor test_sensor;
    private int id;
    @Before
    public void setUP() {
        id = 12345;
        test_sensor = new Sensor(id);
    }
    @Test
    public void getId() {
        Assert.assertEquals(test_sensor.getId(), id);
    }

    @Test
    public void setName() {
        String name = "ABCD";
        test_sensor.setName(name);
        Assert.assertEquals(test_sensor.getName(), name);
    }

    @Test
    public void setValue() {
        double value = 0.123;
        test_sensor.setValue(value);
        Assert.assertTrue(Math.abs(value - test_sensor.getValue()) < 0.001);
    }

    @Test
    public void setTimestamp() {
        String timestamp = "10:10:10 10/10/2010";
        test_sensor.setTimestamp(timestamp);
        Assert.assertEquals(test_sensor.getTimestamp(), timestamp);
    }
}