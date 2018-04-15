public class Sensor {
    private int id;
    private String name;
    private double value;
    private String timestamp;
    private String condition;

    Sensor(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[ Sensor's id: ");
        string.append(id);
        string.append(", Type: " + name + ", Timestamp: " + timestamp + ", Value: ");
        string.append(value);
        string.append(" ]\n");
        String s = string.toString();
        return s;
    }
}
