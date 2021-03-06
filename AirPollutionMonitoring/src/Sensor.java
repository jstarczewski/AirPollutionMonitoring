public class Sensor {
    private int id;
    private String name;
    private double value;
    private String timestamp;

    public Sensor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int setId(int id) {
        this.id = id;
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        String intro = "Wartość " + this.name;
        String time = " o godzinie " + this.timestamp;
        String value = " wynosiła " + this.value;
        if (this.timestamp != null) {
            return intro + time + value;
        }
        return intro + time;
    }
}
