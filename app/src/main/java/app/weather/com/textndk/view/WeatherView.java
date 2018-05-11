package app.weather.com.textndk.view;

import app.weather.com.textndk.bean.WeatherData;

/**
 * Created by mengxj on 2018/4/16.
 */

public interface  WeatherView {

    void showProgress();
    void hideProgress();
    void loadWeather(WeatherData weatherData);
}


