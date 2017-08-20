package com.wds.java8InAction.ch04;

/**
 * Created by wangdongsong1229@163.com on 2017/8/19.
 */
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final String type;

    public Dish(String name, boolean vegetarian, int calories, String type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public String getType() {
        return type;
    }
}
