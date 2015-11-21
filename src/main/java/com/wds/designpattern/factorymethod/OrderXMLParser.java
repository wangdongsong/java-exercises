package com.wds.designpattern.factorymethod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wds on 2015/11/21.
 */
public class OrderXMLParser implements XMLParser {
    private static final Logger LOGGER = LogManager.getLogger(OrderXMLParser.class);

    @Override
    public String parse() {
        LOGGER.info("Parsing order XML...");
        return "Order XML Message";
    }
}
