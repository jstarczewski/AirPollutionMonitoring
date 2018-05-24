import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrentPollution {


    private String cityName;
    private int cityID;
    private List<Sensor> sensorList;
    private static CurrentPollution instance = null;

    /**
     * public static CurrentPollution getInstance() {
     * if (instance == null) {
     * synchronized (CurrentPollution.class) {
     * if (instance == null) {
     * instance = new CurrentPollution();
     * }
     * }
     * }
     * return instance;
     * }
     **/

    private CurrentPollution() {

    }

    public static synchronized CurrentPollution getInstance() {
        if (instance == null) {
            instance = new CurrentPollution();
        }
        return instance;
    }

    /**
     *
     * https://stackoverflow.com/questions/574240/is-there-an-advantage-to-use-a-synchronized-method-instead-of-a-synchronized-blo
     *
     */

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void getPollutionFromChosenSensor(int cityID) {
        this.cityID = cityID;
        sensorList = getSensorList();
        for (Sensor sensor : sensorList) {
            getSensorData(sensor);
        }
    }

    public List<Station> getExistingStations(List<Station> stationsList) {
        String res = getURLfromText("http://api.gios.gov.pl/pjp-api/rest/station/findAll");
        if (res.equals("Input error")) {
            stationsList.add(new Station(0));
            return stationsList;
        }
        Pattern p = Pattern.compile("\"id\":\\d+,\"stationName\":(\".{0,6}?" + cityName + ".*?\")");
        Matcher m = p.matcher(res);
        while (m.find()) {
            stationsList.add(new Station(m.group(0)));
        }
        if (stationsList.isEmpty()) {
            stationsList.add(new Station(1));
        }
        return stationsList;
    }

    private List<Sensor> getSensorList() {
        List<Sensor> sensorList = new ArrayList<>();
        JSONArray sensorArr = null;
        JSONObject sensor = null;
        String toUrl = "http://api.gios.gov.pl/pjp-api/rest/station/sensors/" + cityID;
        try {
            sensorArr = new JSONArray(getURLfromText(toUrl));
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    private void getSensorData(Sensor sensor) {
        try {
            JSONObject data = new JSONObject(getURLfromText("http://api.gios.gov.pl/pjp-api/rest/data/getData/" + sensor.getId()));
            sensor.setName(data.getString("key"));
            JSONArray values = data.getJSONArray("values");
            for (int i = values.length() - 1; i >= 0; i--) {
                JSONObject measurement = values.getJSONObject(i);
                if (!(measurement.isNull("value"))) {
                    sensor.setTimestamp(measurement.getString("date"));
                    sensor.setValue(measurement.getDouble("value"));
                }
            }
        } catch (JSONException e) {
            sensor.setName("Blad odczytu danych");
        }
    }


    public List<Sensor> getPollution() {
        return sensorList;
    }

    private String getURLfromText(String toUrl) {

        StringBuilder response = new StringBuilder();
        String input;
        try {
            URLConnection urlConnection = (new URL(toUrl)).openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader in = new BufferedReader(inputStreamReader);
            while ((input = in.readLine()) != null) {
                response.append(input);
            }
            in.close();
            input = response.toString();
        } catch (Exception e) {
            input = "Input error";
        }
        return input;
    }
}
