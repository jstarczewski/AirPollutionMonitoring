public class Station {

  //  private String data;
    private String stationName;
    private int stationId;

    Station(String data) {
    //    this.data=data;
        stationId = extractStationId(data);
        stationName = extractStationName(data);
    }
    Station(String stationName, int error) {
        this.stationName = stationName;
    }
    private int extractStationId(String data) {
        return Integer.parseInt((data.substring(5).split(",", 2))[0]);
    }
    private String extractStationName(String data) {
        String tmp = data.substring(5).split(",", 2 )[1];
        return tmp.substring(15, tmp.length()-1);
    }
    /**
     * ???
     * */
    public String getStationName() {
        return stationName;
    }
    public int getStationId() {
        return stationId;
    }
    public String toString() {
        return getStationName();
    }
}
