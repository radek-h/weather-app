package sample;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class GeoIP2 {

    private String cityName;
    private String countryName;

    public GeoIP2() {
        try{
            File databaseCity = new File(MyConstants.DATABASE_CITY_PATH);
            DatabaseReader dbReaderCity = new DatabaseReader.Builder(databaseCity).build();
            MyIP ip = new MyIP();
            InetAddress ipAddress = InetAddress.getByName(ip.getMyIP());
            CityResponse response = dbReaderCity.city(ipAddress);
            this.countryName = response.getCountry().getName();
            this.cityName = response.getCity().getName();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeoIp2Exception e) {
            e.printStackTrace();
        }
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }
}
