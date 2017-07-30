package com.wds.async.bestPriceFinder;

import static com.wds.async.bestPriceFinder.Util.delay;
import static com.wds.async.bestPriceFinder.Util.format;

/**
 * Created by wangdongsong1229@163.com on 2017/7/30.
 */
public class Discount {

    public enum Code{
        NONE(0), SILVER(5), GOLD(10), PLANTINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    public static double apply(double price, Code code) {
        delay();
        return format(price * (100 - code.percentage) / 100);
    }


}
