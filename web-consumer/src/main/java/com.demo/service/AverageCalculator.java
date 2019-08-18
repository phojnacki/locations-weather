package com.demo.service;

import com.demo.domain.Location;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.xml.bind.util.JAXBSource;

@Service
public class AverageCalculator {

    public Location locationWithAveragesForWeather(JSONObject weatherResults, String locationMessageData) {
        JSONArray list = weatherResults.getJSONArray("list");

        double sumTemp = 0d, sumHumidity = 0d, sumPressure = 0d;
        for (int i = 0; i < list.length(); i++) {
            JSONObject main = list.getJSONObject(i).getJSONObject("main");
            sumTemp += main.getDouble("temp");
            sumPressure += main.getDouble("pressure");
            sumHumidity += main.getDouble("humidity");
        }

        double avgTemp = sumTemp / list.length();
        double avgHumidity = sumHumidity / list.length();
        double avgPressure = sumPressure / list.length();

        Location result = parseLocationMessage(locationMessageData);

        result.setAverageHumidity(String.valueOf(avgHumidity));
        result.setAverageTemperatur(String.valueOf(avgTemp));
        result.setAveragePressure(String.valueOf(avgPressure));

        return result;
    }

    private Location parseLocationMessage(String locationMessage) {
        Location result = new Location();
        String requestID = locationMessage.substring(0, locationMessage.indexOf("|"));
        String cityName = locationMessage.substring(locationMessage.indexOf("|")+1, locationMessage.indexOf(","));
        String countryCode = locationMessage.substring(locationMessage.indexOf(",")+1);
        result.setRequestId(requestID);
        result.setCityName(cityName);
        result.setCountryCode(countryCode);
        return result;
    }
}
