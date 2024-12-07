package org.anhvt.springbootmasksensitivelog.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.CompositeConverter;

public class LogMaskConverter extends CompositeConverter<ILoggingEvent> {

    @Override
    public String transform(ILoggingEvent event, String in) {
        // Replace sensitive fields with asterisks
        in = in.replaceAll("(?<=firstName=')[^']+?(?=')|(?<=\"firstName\":\")[^\"]+?(?=\")", "****");
        in = in.replaceAll("(?<=lastName=')[^']+?(?=')|(?<=\"lastName\":)\\\"[^\"]+?(?=\")", "****");
        in = in.replaceAll("(?<=age=)\\d+(?=(,|\\s|}))|(?<=\"age\":)\\d+(?=(,|\\s|}))", "****");
        in = in.replaceAll("(?<=creditCardNumber=)\\d+(?=(,|\\s|}))|(?<=\"creditCardNumber\":)\\d+(?=(,|\\s|}))", "****");
        return in;
    }
}