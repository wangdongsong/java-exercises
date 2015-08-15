package com.wds.lambdas.java8_exercises_book;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangdongsong on 15-8-15.
 */
public class Artist {
    private String name;
    private List<Artist> members;
    private String nationality;

    public Artist(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public Artist(String name, List<Artist> members, String nationality) {
        this.name = name;
        this.members = members;
        this.nationality = nationality;
    }

    public Stream<Artist> getMembers() {
        return members.stream();
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public boolean isSolo() {
        return members.isEmpty();
    }

    public boolean isFrom(String nationality) {
        return this.nationality.equals(nationality);
    }

    @Override
    public String toString() {
        return name;
    }

    public Artist copy() {
        List<Artist> members = getMembers().map(Artist::copy).collect(Collectors.toList());
        return new Artist(name, members, nationality);
    }
}
