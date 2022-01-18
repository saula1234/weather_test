package ru.kulkov.weather.dao;

import ru.kulkov.weather.entity.Weather;

import java.util.Date;
import java.util.List;

public interface WeatherDAO {

    public List<Weather> findAll();

    public void save(Weather weather);

}
