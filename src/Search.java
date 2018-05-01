import java.util.List;

public class Search {

    String input;

    private CurrentPollution currentPollution;

    Search() {
        currentPollution = CurrentPollution.getInstance();
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    /**TODO
     *
     * Trzeba zmienic wyrazenie regularne niech sprawdza tez polskie znaki
     * albo jakis inny sposob sprawdzenia
     *
     * Dopisać jakieś dwie metody sprawdzajace czy jest pusty input i czy dlugosc
     * przykladowo wieksza niz 3
     *
     */
    private boolean isCityNameValid(String cityName) {
        return cityName.matches("[a-zA-Z,. ]+");
    }

    public List<Sensor> getPollution(Station station) {
        return currentPollution.getPollutionFromChosenSensors(station.getStationId());
    }

    public List<Station> getStationList() {
        currentPollution.setCityName(input);
        return currentPollution.getExistingStations();
    }

}
