import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Search {

    private String cityName;
    HashMap<String, Integer> cityList;

    Search(String cityName) {
        this.cityName = cityName;
        cityList = new HashMap<>();
        cityList.put("Warszawa", 14);
    }
    public boolean isCityNameValid(String cityName) {
        return true;
    }
    public List<Sensor> getSensorList() {
        CurrentPollution currentPollution = new CurrentPollution(cityList.get(cityName), cityName);
        try {
            return currentPollution.getPollution();
        }
        catch (IOException|JSONException e) {
            return null;
        }
    }

}
