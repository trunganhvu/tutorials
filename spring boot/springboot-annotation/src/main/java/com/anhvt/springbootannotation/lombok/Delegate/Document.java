/**
 * Copyright 2024
 * Name: Document
 */
package com.anhvt.springbootannotation.lombok.Delegate;

import lombok.experimental.Delegate;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/10/2024
 */
public class Document {
    @Delegate(types = Printer.class)
    private final Printer printer = new PrinterImpl();
}
