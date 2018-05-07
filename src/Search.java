import java.util.ArrayList;
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
        System.out.println("jehjksadlk");
        return cityName.matches( "[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*");
    }

    public List<Sensor> getPollution(Station station) {
        return currentPollution.getPollutionFromChosenSensors(station.getStationId());
    }

    public List<Station> getStationList() {
        if(isCityNameValid(input)) {
            currentPollution.setCityName(input);
            return currentPollution.getExistingStations();
        }
        else {
            return (new ArrayList<Station>());
        }
   }
    public boolean isCityNameEmpty(String cityName) {
        if (cityName.isEmpty()) {
            System.out.println("Pustak");
            return true;
        }

        else {
            System.out.println("Nie pustak");
            return false;
        }

    }
    private boolean isNetworkConnectionAviable() {
        return true;
    }

}
