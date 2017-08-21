package com.wds.java8InAction.ch06;

/**
 * Created by wangdongsong1229@163.com on 2017/8/21.
 */
public class Transaction {
    private final Currency currency;
    private final double value;

    public Transaction(Currency currency, double value) {
        this.currency = currency;
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getValue() {
        return value;
    }
}
