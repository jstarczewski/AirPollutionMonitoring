public class Sensor {

    private int id;
    private String name;
    private double value;
    private String timestamp;

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    Sensor(int id) {
        this.id = id;
    }

    public int getId() {
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
