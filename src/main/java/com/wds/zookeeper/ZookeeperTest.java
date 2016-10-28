package com.wds.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Created by wangdongsong1229@163.com on 2016/10/27.
 */
public class ZookeeperTest {

    private static final String URL = "192.168.254.200";
    private static final int sesstionTimeout = 30;
    private static final Watcher WATCHER = new Watcher() {
        @Override
        public void process(WatchedEvent event) {

        }
    };

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //创建节点
        ZooKeeper zooKeeper = new ZooKeeper(URL, sesstionTimeout, WATCHER);
        zooKeeper.create("/hello", "Hello!Zookeeper".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //获取节点中存储的内容
        String value = String.valueOf(zooKeeper.getData("/hello", false, null));

        //删除节点
    }

}
