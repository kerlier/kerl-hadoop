package com.fashion.hadoop.basic;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

public class HadoopApplication {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        conf.set("fs.defaultFS","hdfs://192.168.5.128:9000");

        //需要指定一个用户来操作hdfs
        System.setProperty("HADOOP_USER_NAME","hadoop");

        FileSystem fileSystem = FileSystem.get(conf);

        //FileSystem hadoop = FileSystem.get(new URI("hdfs://192.168.5.128:9000"), conf, "hadoop");

        //上传文件
//        FSDataOutputStream uploadStream = fileSystem.create(new Path("/a.txt"));
//
//        FileInputStream localStream = new FileInputStream("E:\\a.txt");
//
//        IOUtils.copy(localStream,uploadStream);

        //下载文件
        fileSystem.copyToLocalFile(new Path("/a.txt"), new Path("e://a"));

        fileSystem.close();
    }
}
