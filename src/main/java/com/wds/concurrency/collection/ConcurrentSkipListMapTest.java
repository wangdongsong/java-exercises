package com.wds.concurrency.collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jws.soap.SOAPBinding;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * ConcurrentSkipListMap支持并发，并支持Key的排序性
 * Created by wangdongsong1229@163.com on 2017/2/20.
 */
public class ConcurrentSkipListMapTest {
    private static final Logger LOGGER = LogManager.getLogger(ConcurrentSkipListMapTest.class);

    public static void main(String[] args) {
        baseTest();
    }

    private static void baseTest() {
        ConcurrentSkipListMap map = new ConcurrentSkipListMap<UserInfo, String>();
        map.put(new UserInfo(3, "wds3"), "wds3");
        map.put(new UserInfo(4, "wds4"), "wds4");
        map.put(new UserInfo(5, "wds5"), "wds5");
        map.put(new UserInfo(1, "wds1"), "wds1");
        map.put(new UserInfo(2, "wds2"), "wds2");


        new Thread(() -> {
            if (!map.isEmpty()) {
                for (int i = 0; i < 5; i++) {
                    Map.Entry entry = map.pollFirstEntry();
                    UserInfo userInfo = (UserInfo)entry.getKey();
                    LOGGER.info(userInfo.getId() + " --- " + userInfo.getUsername());
                }

            }
        }).start();


    }

    public static class UserInfo implements Comparable<UserInfo> {

        private int id;
        private String username;

        public UserInfo() {
        }

        public UserInfo(int id, String username) {
            this.id = id;
            this.username = username;
        }

        @Override
        public int compareTo(UserInfo o) {
            if (this.getId() < o.getId()) {
                return  -1;
            }
            if (this.getId() > o.getId()) {
                return 1;
            }
            return 0;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + id;
            result = prime * result + ((username == null) ? 0 : username.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            UserInfo other = (UserInfo) obj;
            if (id != other.id) {
                return false;
            }
            if (username == null) {
                if (other.username != null) {
                    return false;
                }
            } else if (!username.equals(other.username)) {
                return false;
            }
            return true;
        }
    }

}
