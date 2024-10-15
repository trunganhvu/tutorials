/**
 * Copyright 2024
 * Name: CannonPrinter
 */
package Facade.service;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/15/2024
 */
public class CannonPrinter {
    private Power power;
    private Printer printer;

    public CannonPrinter() {
        this.power = new Power();
        this.printer = new Printer();
    }

    public void work() {
        power.power();
        printer.print();
    }
}
