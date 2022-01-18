package ru.kulkov.weather.service;

import ru.kulkov.weather.entity.Weather;

import java.util.List;

public interface WeatherService {

    public List<Weather> findAll();

    public void save(Weather weather);
}
