import java.util.ArrayList;
import java.util.List;

public class Search {

    private String input;
    private List<Station> stationsList = new ArrayList<>();
    private CurrentPollution currentPollution;

    Search() {
        currentPollution = CurrentPollution.getInstance();
    }

    public void setInput(String input) {
        this.input = input;
    }

    private boolean isCityNameValid(String cityName) {
        return cityName.matches("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ|\\s]*");
    }

    public void getPollution(Station station) {
        currentPollution.getPollutionFromChosenSensors(station.getStationId());
    }

    public List<Station> getStationList() {
        stationsList.clear();
        if (input.isEmpty()) {
            stationsList.add(new Station(3));
            return stationsList;
        }
        if (!isCityNameValid(input)) {
            stationsList.add(new Station(2));
            return stationsList;
        }
        currentPollution.setCityName(input);
        return currentPollution.getExistingStations(stationsList);
    }
    public List<Station> warnAboutEmptySelection() {
        stationsList.add(new Station(4));
        return stationsList;
    }

}
