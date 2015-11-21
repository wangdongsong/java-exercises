package com.wds.designpattern.factorymethod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wds on 2015/11/21.
 */
public abstract class DisplayService {
    private static final Logger LOGGER = LogManager.getLogger(DisplayService.class);

    public void display() {
        XMLParser parser = getParser();
        String msg = parser.parse();
        LOGGER.info(msg);
    }

    protected abstract XMLParser getParser();
}
