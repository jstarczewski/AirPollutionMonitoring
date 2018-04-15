import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrentPollution {


    private String cityName;
    /**
     * Nie wiem czy ten int jest potrzebny
     */
    private int cityID = 14;
    private List<Sensor> sensorList;
    private static CurrentPollution instance = null;


    private CurrentPollution(String cityName) {
        this.cityName = cityName;
    }

    public static CurrentPollution getInstance(String cityName) {
        if (instance == null) {
            instance = new CurrentPollution(cityName);
        }
        synchronized (CurrentPollution.class) {
            instance = new CurrentPollution(cityName);
        }
        return instance;
    }

    private void fillSensors() {
        sensorList = getSensorList();
        for (Sensor sensor : sensorList) {
            try {
                getSensorData(sensor);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Station> getExistingStations() {
        String allStations = "";
        try {
            allStations = getTextFromUrl(new URL("http://api.gios.gov.pl/pjp-api/rest/station/findAll"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return findMatchingPaterrns(allStations);
    }

    private List<Station> findMatchingPaterrns(String res) {
        List<Station> stationsList = new ArrayList<>();
        Pattern p = Pattern.compile("\"id\":\\d+,\"stationName\":(\".{0,6}?" + cityName + ".*?\")");
        Matcher m = p.matcher(res);
        while (m.find()) {
            stationsList.add(new Station(m.group(0)));
        }
        return stationsList;
    }

    private List<Sensor> getSensorList() {
        List<Sensor> sensorList = new ArrayList<>();
        URL url = null;
        JSONArray sensorArr = null;
        JSONObject sensor = null;
        try {
            url = new URL(("http://api.gios.gov.pl/pjp-api/rest/station/sensors/" + cityID));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            if (url != null) {
                sensorArr = new JSONArray(getTextFromUrl(url));
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        /*
         * Tego assert sensorArr != null nie znałem XD
         * */
        assert sensorArr != null;
        for (int i = 0; i < sensorArr.length(); i++) {
            try {
                sensor = sensorArr.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                assert sensor != null;
                sensorList.add(new Sensor(sensor.getInt("id")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return sensorList;
    }

    private void getSensorData(Sensor sensor) throws IOException, JSONException {
        URL url = new URL(("http://api.gios.gov.pl/pjp-api/rest/data/getData/" + sensor.getId()));
        JSONObject data = new JSONObject(getTextFromUrl(url));
        sensor.setName(data.getString("key"));
        JSONArray values = data.getJSONArray("values");
        for (int i = values.length() - 1; i >= 0; i--) {
            JSONObject measurement = values.getJSONObject(i);
            if (!(measurement.isNull("value"))) {
                sensor.setTimestamp(measurement.getString("date"));
                sensor.setValue(measurement.getDouble("value"));
            }
        }
    }

    public List<Sensor> getPollutionFromChosenSensors(int cityID) {
        this.cityID = cityID;
        fillSensors();
        return sensorList;
    }

    private String getTextFromUrl(URL url) throws IOException {
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"));

        StringBuilder response = new StringBuilder();
        String inputline;

        while ((inputline = in.readLine()) != null)
            response.append(inputline);
        in.close();

        return response.toString();
    }
}
