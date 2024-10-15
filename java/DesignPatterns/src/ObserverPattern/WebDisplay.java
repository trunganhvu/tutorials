/**
 * Copyright 2024
 * Name: WebDisplay
 */
package ObserverPattern;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/15/2024
 */
class WebDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Web display: Current temperature is " + temperature + "°C");
    }
}
