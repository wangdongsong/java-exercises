package com.wds.lambdas.stream.pipeline;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Year;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wds on 2015/10/31.
 */
public class StreamTest {
    private static final Logger LOGGER = LogManager.getLogger(StreamTest.class);

    public static void main(String[] args) {
        //streamProcess();
        //collectProcess();
        LOGGER.info(Book.library().stream().map(Book::getTitle).collect(Collectors.joining(",")));

        List<String> authorsForBooks = Book.library().stream().map(book -> book.getAuthors().stream().collect(Collectors.joining(", ", book.getTitle() + ": ", ""))).collect(Collectors.toList());
        LOGGER.info(authorsForBooks);

    }

    private static void collectProcess() {

        //根据主题对图书进行分类的Map
        Map<Topic, List<Book>> booksByTopic = Book.library().stream().collect(Collectors.groupingBy(Book::getTopic));

        //从图书标题映射到最新版本发布日期的有序Map
        Map<String, Year> titleToPubDate = Book.library().stream().collect(Collectors.toMap(Book::getTitle, Book::getPubDate, BinaryOperator.maxBy(Comparator.<Year>naturalOrder()), HashMap::new));

        //将图书划分为小说（对应true）与非小说(对应false）的Map
        Map<Boolean, List<Book>> fictionOrNon = Book.library().stream().collect(Collectors.partitioningBy(b -> b.getTopic() == Topic.FICTION));

        //将每个主题关联到该主题下拥有最多作者的图书上
        Map<Topic, Optional<Book>> mostAuthorsByTopic = Book.library().stream().collect(Collectors.groupingBy(Book::getTopic, Collectors.maxBy(Comparator.comparing(b -> b.getAuthors().size()))));

        //将每个主题关联到该主题总的卷数上
        Map<Topic, Integer> volumeCountByTopic = Book.library().stream().collect(Collectors.groupingBy(Book::getTopic, Collectors.summingInt(b -> b.getPageCounts().length)));

        //拥有最多图书的主题
        Optional<Topic> mostPopularTopic = Book.library().stream().collect(Collectors.groupingBy(Book::getTopic, Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByKey()).map(Map.Entry::getKey);

        //将每个主题关联到该主题下所有图书标题拼接成字字符串上
        Map<Topic, String> concatenatedTitlesByTopic = Book.library().stream().collect(Collectors.groupingBy(Book::getTopic, Collectors.mapping(Book::getTitle, Collectors.joining(";"))));
    }

    private static void streamProcess() {
        Stream<Book> computingBooks = Book.library().stream().filter(book -> book.getTopic() == Topic.COMPUTING);
        computingBooks.forEach(LOGGER::info);
        LOGGER.info("--------------------");
        Stream<String> bookTitles = Book.library().stream().map(Book::getTitle);
        bookTitles.forEach(LOGGER::info);

        LOGGER.info("--------------------");
        Stream<Book> booksSortedByTitle = Book.library().stream().sorted(Comparator.comparing(Book::getTitle));
        booksSortedByTitle.forEach(LOGGER::info);

        LOGGER.info("--------------------");
        Stream<String> authorsInBookTitleOrder = Book.library().stream().sorted(Comparator.comparing(Book::getTitle))
                .flatMap(book -> book.getAuthors().stream()).distinct();
        authorsInBookTitleOrder.forEach(LOGGER::info);

        LOGGER.info("----------Reading List----------");
        Stream<Book> readingList = Book.library().stream().sorted(Comparator.comparing(Book::getTitle)).limit(2);
        readingList.forEach(LOGGER::info);

        LOGGER.info("---------RemainderList-----------");
        Book.library().stream().sorted(Comparator.comparing(Book::getTitle)).skip(2).forEach(LOGGER::info);

        LOGGER.info("---------Min Book-----------");
        Optional<Book> minBook = Book.library().stream().min(Comparator.comparing(Book::getTitle));
        LOGGER.info(minBook.get());

        Set<String> titles = Book.library().stream().map(Book::getTitle).collect(Collectors.toSet());
        LOGGER.info(titles);
    }
}
