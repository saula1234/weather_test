package ru.kulkov.weather.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kulkov.weather.dao.WeatherDAO;
import ru.kulkov.weather.entity.Weather;
import ru.kulkov.weather.service.WeatherService;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/")
public class WeatherRestController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")//localhost:8081/weather
    public String findAll() throws IOException, ParseException {

        Date date = new Date();

        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        List<Weather> weathers = weatherService.findAll();

        for (Weather w : weathers)
        {
            if (formatForDateNow.format(w.getWeatherDate()).equals((formatForDateNow.format(date))))
                return w.getWeatherValue();
        }

        weatherService.save(new Weather(date, returnWeatherFromYandex()));

        return returnWeatherFromYandex();
    }

    private String returnWeatherFromYandex() throws IOException {
        URL url = new URL("https://export.yandex.ru/bar/reginfo.xml?region=2.xml");
        URLConnection uc = url.openConnection();
        InputStream is = uc.getInputStream();
        String s = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        is.close();
        String[] arr = s.split("\n");
        String weatherCurrent = "";

        for(int j = 0; j<arr.length; j++){
            if (Pattern.matches("<pressure>.*",arr[j].trim())){
                weatherCurrent = arr[j+1].trim();
            }
        }
        String[] ary = weatherCurrent.split("[>.*<]");
        weatherCurrent = ary[2];

        return weatherCurrent;
    }

}
