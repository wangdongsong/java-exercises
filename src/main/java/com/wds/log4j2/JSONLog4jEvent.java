package com.wds.log4j2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.util.StdConverter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.impl.ThrowableProxy;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.ReadOnlyStringMap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by wangdongsong on 2016/10/10.
 */
public class JSONLog4jEvent implements LogEvent{
    //static final String LOG_STASH_ISO8601_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    static final String LOG_STASH_ISO8601_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    static final DateFormat iso8601DateFormat = new SimpleDateFormat(LOG_STASH_ISO8601_TIMESTAMP_FORMAT);

    private LogEvent wrappedLogEvent;

    public JSONLog4jEvent(LogEvent wrappedLogEvent) {
        this.wrappedLogEvent = wrappedLogEvent;
    }

    public String getVersion() {
        return "1";//LOGSTASH VERSION
    }

    /**/
    public String getTimestamp() {
        return iso8601DateFormat.format(new Date(this.getTimeMillis()));
        //return null;
    }


    @Override
    public Map<String, String> getContextMap() {
        return wrappedLogEvent.getContextMap();
    }

    @Override
    public ReadOnlyStringMap getContextData() {
        return null;
    }

    @Override
    public ThreadContext.ContextStack getContextStack() {
        return wrappedLogEvent.getContextStack();
    }

    @Override
    public String getLoggerFqcn() {
        return wrappedLogEvent.getLoggerFqcn();
    }

    @Override
    public Level getLevel() {
        return wrappedLogEvent.getLevel();
    }

    @Override
    public String getLoggerName() {
        return wrappedLogEvent.getLoggerName();
    }

    @Override
    public Marker getMarker() {
        return wrappedLogEvent.getMarker();
    }

    @Override
    @JsonProperty("msg")
    public Message getMessage() {
        return wrappedLogEvent.getMessage();
    }

    @Override
    public long getTimeMillis() {
        return wrappedLogEvent.getTimeMillis();
    }

    @Override
    public StackTraceElement getSource() {
        return wrappedLogEvent.getSource();
    }

    @Override
    public String getThreadName() {
        return wrappedLogEvent.getThreadName();
    }

    @Override
    public long getThreadId() {
        return 0;
    }

    @Override
    public int getThreadPriority() {
        return 0;
    }

    @Override
    public Throwable getThrown() {
        return wrappedLogEvent.getThrown();
    }

    @Override
    public ThrowableProxy getThrownProxy() {
        return wrappedLogEvent.getThrownProxy();
    }

    @Override
    public boolean isEndOfBatch() {
        return wrappedLogEvent.isEndOfBatch();
    }

    @Override
    public boolean isIncludeLocation() {
        return wrappedLogEvent.isIncludeLocation();
    }

    @Override
    public void setEndOfBatch(boolean endOfBatch) {
        wrappedLogEvent.setEndOfBatch(endOfBatch);

    }

    @Override
    public void setIncludeLocation(boolean locationRequired) {
        wrappedLogEvent.setIncludeLocation(locationRequired);
    }

    @Override
    public long getNanoTime() {
        return wrappedLogEvent.getNanoTime();
    }

    /**
     * Converter used by JsonSerilize annotation on mixin.
     */
    public static class LogEventToLogStashLogEventConverter extends StdConverter<LogEvent, JSONLog4jEvent> {

        @Override
        public JSONLog4jEvent convert(LogEvent value) {
            return new JSONLog4jEvent(value);
        }
    }
}
