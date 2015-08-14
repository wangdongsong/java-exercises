package com.wds.concurrency.base.immutableObjects;

/**
 * <pre>
 * 定义不可变对象的规则：
 * 1、不提供setter方法
 * 2、将类所有字段定义为final、private
 * 3、不允许子类重写方法，简单的办法是将类声明为final，更好的方法是将构造函数声明为私有的，通过工厂方法创建对象
 * 4、如果类的字段是可变对象的引用，不允许修改被引用对象
 *      （1）、不提供修改可象的方法
 *      （2）、不共享可变对象的引用，当一个引用被当做参数传递给构造函数，而这个引用指向的是一个外部的可变对象时，
 *             一定要保存这个引用。如果必须要保存，那么创建可变对象的复本，然后保存复本对象的引用，同样如果需要
 *             返回内部的可变对象时，不要返回可变对象本身，而返回其复本。
 *
 * 根据上述规则，修改SynchronizedRGB类：
 *  1、去掉setter方法。
 *  2、所有字段都是私有，并且final。
 *  3、将类声明为final。
 *  4、只有一个字段是对象引用，并且被引用的对象也是不可变对象。
 *
 * </pre>
 * Created by wangdongsong on 15-8-9.
 */
public final class ImmutableRGB {

    final private int red;
    final private int green;
    final private int blue;
    final private String name;

    private void check(int red, int green, int blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public ImmutableRGB(int red, int green, int blue, String name) {
        this.blue = blue;
        this.red = red;
        this.green = green;
        this.name = name;
    }

    public int getRGB() {
        return (red << 16 | (green << 8) | blue);
    }

    public String getName(){
        return name;
    }

    public ImmutableRGB invert() {
        return new ImmutableRGB(255 - red, 255 - green, 255 - blue, "Inverse of" + name);
    }
}
