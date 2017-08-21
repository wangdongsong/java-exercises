package com.wds.java8InAction.ch06;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 按照货币进行分组
 * Created by wangdongsong1229@163.com on 2017/8/21.
 */
public class GroupingTransactionTest {

    public static List<Transaction> transactions = Arrays.asList( new Transaction(Currency.EUR, 1500.0),
            new Transaction(Currency.USD, 2300.0),
            new Transaction(Currency.GBP, 9900.0),
            new Transaction(Currency.EUR, 1100.0),
            new Transaction(Currency.JPY, 7800.0),
            new Transaction(Currency.CHF, 6700.0),
            new Transaction(Currency.EUR, 5600.0),
            new Transaction(Currency.USD, 4500.0),
            new Transaction(Currency.CHF, 3400.0),
            new Transaction(Currency.GBP, 3200.0),
            new Transaction(Currency.USD, 4600.0),
            new Transaction(Currency.JPY, 5700.0),
            new Transaction(Currency.EUR, 6800.0) );

    public static void main(String[] args) {
        groupByLambda();
        groupImperatively();
    }

    /**
     * 传统写法
     */
    private static void groupImperatively() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }
    }

    private static void groupByLambda() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));
    }

}
