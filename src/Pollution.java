import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.List;
import org.json.*;

public class Pollution {



    private String cityID, cityName;

    public Pollution(String cityID, String cityName){
        this.cityID = cityID;
        this.cityName = cityName;
    }


    private void wgetDataFromSensors() throws MalformedURLException{
        List<Pollutions> sensorList = wgetSensorList();

        for (Pollutions sensor: sensorList)
        {
            URL url = new URL("http://api.gios.gov.pl/pjp-api/rest/data/getData/"+sensor.getId());
            //TODO
        }

    }

    private List<Pollutions> wgetSensorList() throws MalformedURLException {

        List<Pollutions> sensorList = new ArrayList<Pollutions>();
        URL url = new URL("http://api.gios.gov.pl/pjp-api/rest/station/sensors/" + cityID);
        JSONArray sensorArr = new JSONArray(url);

        for ( int i = 0; i < sensorArr.length(); i++ )
        {
            JSONObject sensor = sensorArr.getJSONObject(i);
            sensorList.add(new Pollutions(sensor.getString("id")));
        }

        return sensorList;
    }

    private void parseData(){

    }

    public void main(){

    }
}
