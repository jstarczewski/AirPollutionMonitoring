import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
    private int cityID;
    private List<Sensor> sensorList;
    private static CurrentPollution instance = null;


    //private CurrentPollution(String cityName) {
    //      this.cityName = cityName;
//    }

    /**
     * public static synchronized CurrentPollution getInstance(String cityName) {
     * if (instance == null) {
     * instance = new CurrentPollution(cityName);
     * }
     * return instance;
     * }
     **/
    public static CurrentPollution getInstance() {
        if (instance == null) {
            synchronized (CurrentPollution.class) {
                if (instance == null) {
                    instance = new CurrentPollution();
                }
            }
        }
        return instance;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    private void fillSensors() {
        sensorList = getSensorList();
        for (Sensor sensor : sensorList) {
            getSensorData(sensor);
        }
    }

    /*
     * praca nad obsluga bledow
     *
     * */

    public List<Station> getExistingStations() {
        String res = getURLfromText("http://api.gios.gov.pl/pjp-api/rest/station/findAll");
        List<Station> stationsList = new ArrayList<>();
        System.out.println(res);
        if (res.equals("d")) {
            System.out.println("tutu");
            stationsList.add(new Station("Blad podczas odczytu danych z internetu", 0));
            return stationsList;
        }
        Pattern p = Pattern.compile("\"id\":\\d+,\"stationName\":(\".{0,6}?" + cityName + ".*?\")");
        Matcher m = p.matcher(res);
        while (m.find()) {
            stationsList.add(new Station(m.group(0)));
        }
        if (stationsList.isEmpty()) {
            stationsList.add(new Station("Poszukiwana stacja nie istnieje oraz nie zanleziono żadnej podobnej", 1));
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

    private void getSensorData(Sensor sensor) {
        try {
            JSONObject data = new JSONObject("http://api.gios.gov.pl/pjp-api/rest/data/getData/" + sensor.getId());
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

    public List<Sensor> getPollutionFromChosenSensors(int cityID) {
        this.cityID = cityID;
        fillSensors();
        return sensorList;
    }

    public List<Sensor> getPollution() {
        return sensorList;
    }

    /**
     * bufferedReader do poprawy
     **/

    private String getURLfromText(String toUrl) {

        StringBuilder response = new StringBuilder();
        String input = "";
        try {
            URLConnection urlConnection = (new URL(toUrl)).openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader in = new BufferedReader(inputStreamReader);
            while ((input = in.readLine()) != null) {
                System.out.println("penta");
                response.append(input);
            }
            in.close();
            System.out.println(response.toString());
            input = response.toString();
        } catch (Exception e) {
            input = "d";
        }
        return input;
    }
}
