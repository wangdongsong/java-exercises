package com.wds.lambdas.java8_exercises_book;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangdongsong on 15-8-15.
 */
public class TestChapter3 {

    public Set<String> findLongTracks(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        for (Album album : albums) {
            for (Track track : album.getTrackList()) {
                if (track.getLength() > 60) {
                    String name = track.getName();
                    trackNames.add(name);
                }
            }
        }
        return trackNames;
    }


    public Set<String> findLongTracks2(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();

        albums.stream().forEach(album -> {
            album.getTracks().forEach(track -> {
                if (track.getLength() > 60) {
                    String name = track.getName();
                    trackNames.add(name);
                }
            });
        });

        return trackNames;
    }

    public Set<String> findLongTracks3(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        albums.stream().forEach(album -> {
            album.getTracks().filter(track -> track.getLength() > 60)
                    .map(track1 -> track1.getName())
                    .forEach(name -> trackNames.add(name));
        });
        return trackNames;
    }

    public Set<String> findLongTrack4(List<Album> albums) {
        return albums.stream().flatMap(album -> album.getTracks())
                .filter(track -> track.getLength() > 60)
                .map(track1 -> track1.getName())
                .collect(Collectors.toSet());
    }

    /**
     * 使用reduce对一个数字列表求和
     * @param numbers
     * @return
     */
    public static int addUp(Stream<Integer> numbers) {
        int sum = 0;
        //sulotion 1
//        sum = numbers.reduce(0, (accumulator, x) -> accumulator + x);

        //sulotion 2
        int sum2 = numbers.map(number -> number).mapToInt(number -> number).sum();
        System.out.println(sum2);

        return sum;
    }

    public static List<String> getNamesAndOrigin(List<Artist> artists) {
        return artists.stream().flatMap(artist -> Stream.of(artist.getName(), artist.getNationality())).collect(Collectors.toList());
    }

    public static int membersCount(List<Artist> list) {
        int totalMember = 0;

        //sulotion 1
        for (Artist artist : list) {
            Stream<Artist> members = artist.getMembers();
            totalMember += members.count();
        }

        System.out.println(totalMember);

        //sulotion2
        totalMember = list.stream().map(artist -> artist.getMembers().count()).reduce(0L, Long::sum).intValue();

        System.out.println(totalMember);

        return totalMember;
    }

    /**
     * 计算字符串中小写字母的个数
     * @param string
     * @return
     */
    public static int countLowercaseChars(String string) {
        return (int) string.chars().filter(Character::isLowerCase).count();
    }

    /**
     * 计算机strings列表中包含小写字符最多的字符串
     * @param strings
     * @return
     */
    public static Optional<String> mostLowercaseString(List<String> strings) {
        return strings.stream().max(Comparator.comparing(TestChapter3::countLowercaseChars));
    }

    /**
     * 使用reduce实现map操作
     * @param stream
     * @param mapper
     * @param <I>
     * @param <O>
     * @return
     */
    public static <I, O> List<O> mapUseReduce(Stream<I> stream, Function<I, O> mapper) {
        return stream.reduce(new ArrayList<O>(), (acc, x) -> {

            List<O> newAcc = new ArrayList<O>(acc);
            newAcc.add(mapper.apply(x));

            return newAcc;

        }, (List<O> left, List<O> right) -> {

            List<O> newLeft = new ArrayList<O>(left);
            newLeft.addAll(right);

            return newLeft;
        });
    }

    /**
     * 使用reduce实现filter操作
     * @param stream
     * @param predicate
     * @param <I>
     * @return
     */
    public static <I> List<I> filter(Stream<I> stream, Predicate<I> predicate) {
        List<I> initial = new ArrayList<>();
        return stream.reduce(initial, (List<I> acc, I x) -> {
            if (predicate.test(x)) {
                List<I> newAcc = new ArrayList<I>(acc);
                newAcc.add(x);
                return newAcc;
            } else {
                return acc;
            }
        }, TestChapter3::combineLists);
    }

    private static <I> List<I> combineLists(List<I> left, List<I> right) {
        List<I> newLeft = new ArrayList<>(left);
        newLeft.addAll(right);
        return newLeft;
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(addUp(numbers.stream()));

        List<Artist> lists = new ArrayList<>();
        Artist artist = new Artist("wds", new ArrayList<>(), " wds");
        lists.add(artist);

        artist = new Artist("lsk", new ArrayList<>(), "lsk");
        lists.add(artist);
        System.out.println(getNamesAndOrigin(lists));

        membersCount(lists);

    }

}
