package com.wds.designpattern.factorymethod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wds on 2015/11/21.
 */
public class ResponseXMLParser implements XMLParser {
    private static final Logger LOGGER = LogManager.getLogger(ResponseXMLParser.class);

    @Override
    public String parse() {
        LOGGER.info("Parsing response XML...");
        return "Response XML Message";
    }
}
