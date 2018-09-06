package sample;

import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import org.apache.commons.logging.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Weather {

    private static String city;
    private static String country;
    private static OWM owm;


    public Weather(String city,String country){
        this.city = city;
        this.country = country;
        this.owm = new OWM("a238c83339bc19c494dcff1f93f5865f");
    }

    public boolean checkCity() {

        try {
            CurrentWeather cwd = owm.currentWeatherByCityName(city);
            if (cwd.hasRespCode() && cwd.getRespCode() == 200) {
                if (cwd.hasCityName())
                    return true;
            }
        } catch (APIException e) {
            return false;
        }
        return true;
    }

    public ArrayList<String> getTemperatures(JSONObject jsonObject) {

        ArrayList<Double> allTemperatures = new ArrayList<>();
        ArrayList<String> temperatures = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.#");
        String str = null;
        Double dbl = null;

        JSONArray jsonArray = jsonObject.getJSONArray("list");
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jObj = jsonArray.getJSONObject(i);
            JSONObject jObj1= jObj.getJSONObject("main");
            allTemperatures.add(jObj1.getDouble("temp"));
        }

        for(int i = 0; i <= allTemperatures.size(); i=i+8)
        {
            if(i == 40) {
                dbl = allTemperatures.get(i-1)-272;
            } else{
                dbl = allTemperatures.get(i)-272;
            }
            str = Double.toString(dbl).substring(0,4) +"°C";
            temperatures.add(str);
        }
        return temperatures;
    }

    public ArrayList<String> getDayNames (JSONObject jsonObject) throws ParseException {

        ArrayList<String> dayNamesTemp = new ArrayList<>();
        ArrayList<String> dayNames = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        String str = null;
        Date dt = null;
        DateFormat dateFormat = new SimpleDateFormat("EEE.", Locale.ENGLISH);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        JSONArray jsonArray = jsonObject.getJSONArray("list");
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jObj = jsonArray.getJSONObject(i);
            dayNamesTemp.add(jObj.getString("dt_txt").substring(0,10));
        }

        for(int i = 0; i <= dayNamesTemp.size(); i=i+8)
        {
            if(i == 40)
                str = dayNamesTemp.get(i-1);
            else
                str = dayNamesTemp.get(i);

            dt = format.parse(str);
            str = dateFormat.format(dt);
            dayNames.add(str);

        }
        return dayNames;
    }
    public JSONObject JsonParser() throws IOException {

        String sUrl = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid="+ MyConstants.OWM_API_KEY +"";
        URL url = new URL(sUrl);
        URLConnection request = url.openConnection();
        request.connect();

        BufferedReader in = new BufferedReader( new InputStreamReader(request.getInputStream(),"UTF8"));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);
        in.close();

        JSONObject jsonObject = new JSONObject(response.toString());
        return jsonObject;
    }

    public ArrayList<String> getIconCodes(JSONObject jsonObject) {

        ArrayList<String> AllForecastWeatherIconNames = new ArrayList<>();
        ArrayList<String> ForecastWeatherIconNames = new ArrayList<>();
        String str = null;

        JSONArray jsonArray = jsonObject.getJSONArray("list");
        int length = jsonArray.length();
        for(int i = 0; i < length; i++) {
            JSONObject jObj = jsonArray.getJSONObject(i);
            JSONArray jsonArray1 = jObj.getJSONArray("weather");
            int length1 = jsonArray1.length();

            for(int j=0; j < length1; j++) {
                JSONObject jObj1 = jsonArray1.getJSONObject(j);
                AllForecastWeatherIconNames.add(jObj1.getString("icon"));
            }
        }

        for(int i = 0; i <= AllForecastWeatherIconNames.size(); i=i+8)
        {
            if(i == 40)
                str = AllForecastWeatherIconNames.get(i-1);
            else
                str = AllForecastWeatherIconNames.get(i);
            ForecastWeatherIconNames.add(str);
        }
        return ForecastWeatherIconNames;
    }
}
