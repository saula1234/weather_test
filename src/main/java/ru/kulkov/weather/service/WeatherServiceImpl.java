package ru.kulkov.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kulkov.weather.dao.WeatherDAO;
import ru.kulkov.weather.entity.Weather;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherDAO weatherDAO;

    @Override
    @Transactional
    public List<Weather> findAll() {
        return weatherDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Weather weather) {
        weatherDAO.save(weather);
    }
}
