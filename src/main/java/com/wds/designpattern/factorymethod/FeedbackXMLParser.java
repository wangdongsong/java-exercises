package com.wds.designpattern.factorymethod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wds on 2015/11/21.
 */
public class FeedbackXMLParser implements XMLParser {
    private static final Logger LOGGER = LogManager.getLogger(FeedbackXMLParser.class);

    @Override
    public String parse() {
        LOGGER.info("Parsing feedback XML...");

        return "Feedback XML Message";
    }
}
