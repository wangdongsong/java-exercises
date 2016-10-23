package com.wds.hadoop;

import java.io.*;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URISyntaxException;

/**
 * Created by wangdongsong1229@163.com on 2016/10/17.
 */
public class HDFSTest {
    private static final Logger LOGGER = LogManager.getLogger(HDFSTest.class);
    static {
        System.setProperty("HADOOP_USER_NAME", "hadoop");
    }

    public static void main(String[] args) {
        //uploadFile();
        appendFile();
    }

    /**
     * 追加文件
     */
    private static void appendFile() {
        Configuration conf = new Configuration();
        System.setProperty("HADOOP_USER_NAME", "hadoop");
        try {
            URI uri = new URI("hdfs://192.168.254.200:9000");

            FileSystem fileSystem = FileSystem.get(uri, conf);
            Path src = new Path("files/hdfsTest.txt");
            Path dest = new Path("/tmp/hdfsText2.txt");

            InputStream is = new BufferedInputStream(new FileInputStream(new File("files/hdfsTest.txt")));
            OutputStream os = fileSystem.append(dest);
            byte[] bytesContent = new byte[2048];
            while ((is.read(bytesContent)) != -1) {
                os.write(bytesContent);
            }
            is.close();
            os.close();
            fileSystem.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (URISyntaxException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 查看集群中的节点
     */
    private static void getHDFSList() {
        Configuration conf = new Configuration();
        try {
            FileSystem hdfs = FileSystem.get(conf);
            DistributedFileSystem dfs = (DistributedFileSystem) hdfs;
            DatanodeInfo[] datanodeInfo = dfs.getDataNodeStats();

            String[] names = new String[datanodeInfo.length];
            for (int i = 0; i < names.length; i++) {
                names[i] = datanodeInfo[i].getHostName();
                LOGGER.info("node " + i + " name " + names[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看文件在HDFS集群的位置
     */
    private static void fileLocation() {
        Configuration conf = new Configuration();
        try {
            FileSystem hdfs = FileSystem.get(conf);
            Path path = new Path("/tmp/");
            FileStatus fileStatus = hdfs.getFileStatus(path);

            BlockLocation[] blkLocations = hdfs.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
            int blockLen = blkLocations.length;

            for (int i = 0; i < blockLen; i++) {
                String[] hosts = blkLocations[i].getHosts();
                LOGGER.info("block" + i + " location " + hosts[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     */
    private static void uploadFile() {
        Configuration conf = new Configuration();
        System.setProperty("HADOOP_USER_NAME", "hadoop");
        try {
            //conf.set("hadoop.home.dir", "F:\\Software\\hadoop-2.7.2\\hadoop-2.7.2");
            URI uri = new URI("hdfs://192.168.254.200:9000");

            FileSystem fileSystem = FileSystem.get(uri, conf);
            Path src = new Path("files/hdfsTest.txt");
            Path dest = new Path("/tmp/hdfsText2.txt");
            fileSystem.copyFromLocalFile(src, dest);

            fileSystem.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (URISyntaxException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
