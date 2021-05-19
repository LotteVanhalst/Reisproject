package be.kdg.reisproject.logging;

import sun.plugin2.message.Message;

import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * @author Lotte Vanhalst
 * @version 1.0 9/04/2019 22:04
 */
public class SmallLogFormatter extends java.util.logging.Formatter {
    private Logger logger = Logger.getLogger("be.kdg.logging.SmallLogFormatter");

    @Override
    public String format (LogRecord record){
        LocalDateTime localDateTime = Instant.ofEpochMilli(record.getMillis()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        String datum = localDateTime.toString().replace("T", " ");
        String message = MessageFormat.format(record.getMessage(), record.getParameters());
        System.out.print(String.format("%s Level: %s melding: %s %n", datum, record.getLevel(), message));
        return (datum + " Level: " + record.getLevel() + " melding: " + message);
    }
}
