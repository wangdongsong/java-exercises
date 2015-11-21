package com.wds.designpattern.factorymethod;

/**
 * Created by wds on 2015/11/21.
 */
public class Test {

    public static void main(String[] args) {
        DisplayService service = new FeedbackXMLDisplayService();
        service.display();

        service = new ErrorXMLDisplayService();
        service.display();

        service = new OrderXMLDisplayService();
        service.display();

        service = new ResponseXMLDisplayService();
        service.display();
    }
}
