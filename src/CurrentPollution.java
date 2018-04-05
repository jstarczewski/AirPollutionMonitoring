import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class CurrentPollution {



    private String cityName;
    private int cityID;
    private List<Sensor> sensorList;


    CurrentPollution(int cityID, String cityName){
        this.cityID = cityID;
        this.cityName = cityName;
    }


    private void fillSensors() throws IOException, JSONException {
        sensorList = getSensorList();

        for (Sensor sensor: sensorList)
        {
            getSensorData(sensor);
        }
    }

    private List<Sensor> getSensorList() throws IOException, JSONException {



        List<Sensor> sensorList = new ArrayList<Sensor>();
        URL url = new URL(("http://api.gios.gov.pl/pjp-api/rest/station/sensors/" + cityID));
        JSONArray sensorArr = new JSONArray(getTextFromUrl(url));

        for ( int i = 0; i < sensorArr.length(); i++ )
        {
            JSONObject sensor = sensorArr.getJSONObject(i);
            sensorList.add(new Sensor(sensor.getInt("id")));
        }

        return sensorList;
    }


    private void getSensorData(Sensor sensor) throws IOException, JSONException {
        URL url = new URL(("http://api.gios.gov.pl/pjp-api/rest/data/getData/"+sensor.getId()));
        JSONObject data = new JSONObject(getTextFromUrl(url));
        sensor.setName(data.getString("key"));
        JSONArray values = data.getJSONArray("values");
        for (int i = values.length()-1; i >= 0; i--) {
            JSONObject measurement = values.getJSONObject(i);
            if( !(measurement.isNull("value")) ){
                sensor.setTimestamp(measurement.getString("date"));
                sensor.setValue(measurement.getDouble("value"));
            }
        }
    }

    public List<Sensor> getPollution() throws IOException, JSONException {
        fillSensors();
   /*     for (Sensor sensor: sensorList)
        {
            System.out.println(sensor);
        }*/
        return sensorList;
    }

    private String getTextFromUrl(URL url) throws IOException
    {
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"));

        StringBuilder response = new StringBuilder();
        String inputline;

        while((inputline = in.readLine()) != null)
            response.append(inputline);

        in.close();

        return response.toString();

    }

 /*   public static void main(String []args) throws IOException, JSONException {
        CurrentPollution city = new CurrentPollution(14,"Warszawa");

        city.getPollution();
    }*/
}
