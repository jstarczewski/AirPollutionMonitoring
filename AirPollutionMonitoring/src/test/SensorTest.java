package test;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SensorTest {
    private int id;

    @Test
    public void getName() throws Exception {
        String name = "Wroclaw";
        setName(name);
        Assert.assertEquals("testWroclaw", "Wroclaw", getName(name));
    }


    @Test
    public void setId() throws Exception {
         int id = 3;
        setId(id);
        Assert.assertEquals("4", "3", getId(id));
    }


    @Test
    public void getId() throws Exception {
        id = 2;
        setId(id);
        Assert.assertEquals("3", "2", getId(id));
    }

    private int setId(int id) {
        this.id = id;
        return id;
    }

    private int getId(int id) {
        return id;
    }

    @Test
    public void setName() throws Exception {
        String name = "Wroclaw";
        setName(name);
        Assert.assertEquals("notWroclaw", "Wroclaw", getName(name));

    }

    private String getName(String name) {
        return name;
    }

    private String setName(String name) {
        return name;
    }

    @Test
    public void setValue() throws Exception {
    }

    @Test
    public void setTimestamp() throws Exception {
    }

}