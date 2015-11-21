package com.wds.designpattern.factorymethod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wds on 2015/11/21.
 */
public class ErrorXMLParser implements XMLParser {
    private static final Logger LOGGER = LogManager.getLogger(ErrorXMLParser.class);

    @Override
    public String parse() {
        LOGGER.info("Parsing error XML...");

        return "Error XML Message";
    }
}
