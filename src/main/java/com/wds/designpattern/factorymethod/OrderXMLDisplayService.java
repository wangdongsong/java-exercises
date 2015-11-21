package com.wds.designpattern.factorymethod;

/**
 * Created by wds on 2015/11/21.
 */
public class OrderXMLDisplayService extends DisplayService {

    @Override
    protected XMLParser getParser() {
        return new OrderXMLParser();
    }
}
