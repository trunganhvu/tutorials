/**
 * Copyright 2024
 * Name: WeatherStation
 */
package ObserverPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/15/2024
 */
// Subject class
class WeatherStation {
    private List<Observer> observers;
    private float temperature;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}
