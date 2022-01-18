package ru.kulkov.weather.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kulkov.weather.entity.Weather;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public class WeatherDOAImpl implements WeatherDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Weather> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Weather> weatherQuery = currentSession.createQuery("from Weather", Weather.class);
        List<Weather> weathers = weatherQuery.getResultList();
        return weathers;
    }

    @Override
    public void save(Weather weather) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(weather);
    }



}
