package com.wds.lambdas.java8_exercises_book;

/**
 * Created by wangdongsong on 15-8-15.
 */
public final class Track {
    private final String name;
    private final int length;

    public Track(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public Track copy() {

        return new Track(name, length);

    }
}
