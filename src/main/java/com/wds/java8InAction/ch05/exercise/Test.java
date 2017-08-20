package com.wds.java8InAction.ch05.exercise;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by wangdongsong1229@163.com on 2017/8/20.
 */
public class Test {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 201, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 201, 950)
        );

        //找出2011年的所有交易并按交易额排序
        transactions.stream().filter(t -> t.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());

        //交易员都在哪些不同的地市工作过
        transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(Collectors.toList());
        transactions.stream().map(transaction -> transaction.getTrader().getCity()).collect(Collectors.toSet());

        //查找来自剑桥的交易员，并按姓名排序
        transactions.stream().map(transaction -> transaction.getTrader()).filter(trader -> trader.getCity().equalsIgnoreCase("Cambridge")).distinct().sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toSet());

        //返回所有交易员的姓名字符吕，按字段顺序排序
        transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().reduce("", (n1, n2) -> n1 + n2);
        //更为高效的方法
        transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().collect(Collectors.joining());

        //有无交易员在米兰工作的
        boolean milanBased = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("Milan"));

        //打印生活在剑桥的交易员的所有交易额
        transactions.stream().filter(t -> t.getTrader().getCity().equalsIgnoreCase("Cambridge")).map(Transaction::getValue).forEach(System.out::println);

        //所有交易中，最高的交易额
        Optional<Integer> highestValue = transactions.stream().map(Transaction::getValue).reduce(Integer::max);

        //查找最小交易额
        Optional<Transaction> smallestTransaction = transactions.stream().reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        //其它方法
        Optional<Transaction> smallestTransaction2 = transactions.stream().min(Comparator.comparing(Transaction::getValue));



    }
}
