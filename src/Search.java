import java.util.List;

public class Search {

    private CurrentPollution currentPollution;

    Search(String input) {
        currentPollution = CurrentPollution.getInstance(input);
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
        return currentPollution.getExistingStations();
    }

}
