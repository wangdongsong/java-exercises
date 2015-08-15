package com.wds.lambdas.java8_exercises_book;

import java.util.stream.Stream;

/**
 * Created by wangdongsong on 15-8-15.
 */
public interface Performance {

    public String getName();

    public Stream<Artist> getMusicians();

    public default Stream<Artist> getAllMusicians() {
        return getMusicians().flatMap(artist -> {
            return Stream.concat(Stream.of(artist), artist.getMembers());
        });
    }
}
