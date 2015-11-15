package com.wds.lambdas.stream.pipeline;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wds on 2015/10/31.
 */
public class Book {
    private String title;
    private List<String> authors;
    private int[] pageCounts;
    private Topic topic;
    private Year pubDate;
    private double height;

    public Book(String title, List<String> authors, int[] pageCounts, Topic topic, Year pubDate, double height) {
        this.title = title;
        this.authors = authors;
        this.pageCounts = pageCounts;
        this.topic = topic;
        this.pubDate = pubDate;
        this.height = height;
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public static List<Book> library() {
        List<Book> books = new ArrayList<>();

        Book nails = new Book("Fundamentals of Chinese Fingernail Image", Arrays.asList("Li", "Fu", "Li"),
                new int[]{256}, Topic.HISTORY, Year.of(2015), 25.2);
        Book dragon = new Book("Compilers: Principles, Techniques asn Tools", Arrays.asList("Aho", "Lam","Sethi", "Fu", "Li"),
                new int[]{1009}, Topic.COMPUTING, Year.of(1986), 23.8);

        Book voss = new Book("Voss", Arrays.asList("Voss White"),
                new int[]{487}, Topic.MEDICINE, Year.of(1998), 35);

        Book lotr = new Book("Lotr", Arrays.asList("Tolkien"),
                new int[]{531, 416, 624}, Topic.FICTION, Year.of(1935), 24.0);

        books.add(nails);
        books.add(dragon);
        books.add(voss);
        books.add(lotr);

        return books;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public int[] getPageCounts() {
        return pageCounts;
    }

    public void setPageCounts(int[] pageCounts) {
        this.pageCounts = pageCounts;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Year getPubDate() {
        return pubDate;
    }

    public void setPubDate(Year pubDate) {
        this.pubDate = pubDate;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
