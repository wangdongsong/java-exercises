package com.wds.log4j2;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.core.impl.LogEventFactory;
import org.apache.logging.log4j.core.impl.ThrowableProxy;
import org.apache.logging.log4j.message.Message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangdongsong1229@163.com on  2016/10/10.
 */
public class JSONLog4jEventFactory implements LogEventFactory{

    private static final JSONLog4jEventFactory instance = new JSONLog4jEventFactory();

    public static JSONLog4jEventFactory getInstance() {
        return instance;
    }

    /**
     * Creates a log event.
     *
     * @param loggerName The name of the Logger.
     * @param marker An optional Marker.
     * @param fqcn The fully qualified class name of the caller.
     * @param level The event Level.
     * @param data The Message.
     * @param properties Properties to be added to the log event.
     * @param t An optional Throwable.
     * @return The LogEvent.
     */
    @Override
    public LogEvent createEvent(final String loggerName, final Marker marker,
                                final String fqcn, final Level level, final Message data,
                                final List<Property> properties, final Throwable t) {
        return new JSONLog4jEvent(new Log4jLogEvent(loggerName, marker, fqcn, level, data, properties, t));
    }
}
