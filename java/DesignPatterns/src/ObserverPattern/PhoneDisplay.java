/**
 * Copyright 2024
 * Name: PhoneDisplay
 */
package ObserverPattern;

/**
 * Concrete Observer
 *
 * @author trunganhvu
 * @date 10/15/2024
 */
class PhoneDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Phone display: Current temperature is " + temperature + "°C");
    }
}
