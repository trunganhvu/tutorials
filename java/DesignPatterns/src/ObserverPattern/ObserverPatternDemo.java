/**
 * Copyright 2024
 * Name: ObserverPatternDemo
 */
package ObserverPattern;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/15/2024
 */
public class ObserverPatternDemo {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        Observer phoneDisplay = new PhoneDisplay();
        Observer webDisplay = new WebDisplay();

        weatherStation.addObserver(phoneDisplay);
        weatherStation.addObserver(webDisplay);

        weatherStation.setTemperature(25.0f);
        weatherStation.setTemperature(30.0f);
    }
}
