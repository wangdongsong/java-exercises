package com.wds.java8InAction.ch03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** ch03 Lambda表达式
 * Created by wangdongsong1229@163.com on 2017/8/18.
 */
public class Test {
    private static final Logger LOGGER = LogManager.getLogger(Test.class);

    public static void main(String[] args) {

        //匿名函数
        Runnable r = () -> System.out.println("HelloWorld2");
        r.run();

        /**
         * 3.3 使用函数式接口传递行为
         */
        //自定义函数接口
        //以读取文件为例
        processFile();
        //等同于processFile()方法
        processFile((BufferedReader br) -> br.readLine());
        //读取两行文件
        processFile((BufferedReader br) -> br.readLine() + br.readLine());
    }

    /**
     * 只能返回一行，如果返回两行就要重新编写方法，可通过函数式接口传递行为
     * @return
     */
    public static String processFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("ReadMe.markdown"))) {
            return br.readLine();
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 通过函数式接口传递行为
     * @param brp
     * @return
     */
    public static String processFile(BufferedReaderProcessor brp){
        try (BufferedReader br = new BufferedReader(new FileReader("ReadMe.markdown"))) {
            return brp.process(br);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
