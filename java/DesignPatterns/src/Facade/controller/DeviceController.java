/**
 * Copyright 2024
 * Name: DeviceController
 */
package Facade.controller;

import Facade.service.CannonPrinter;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/15/2024
 */
public class DeviceController {
    public static void main(String[] args) {
        CannonPrinter cannonPrinter = new CannonPrinter();
        cannonPrinter.work();
    }
}
