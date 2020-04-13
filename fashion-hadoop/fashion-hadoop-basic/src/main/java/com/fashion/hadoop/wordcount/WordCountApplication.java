package com.fashion.hadoop.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountApplication {

    public static void main(String[] args) throws  Exception {
        Configuration conf = new Configuration();
        conf.set("mapreduce.framework.name","local");

        Job job = Job.getInstance(conf);

        //设置class
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //设置map输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置最终的输出类型
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置输入path
        FileInputFormat.setInputPaths(job,"E:\\wordcount\\input");

        //设置输出path
        FileOutputFormat.setOutputPath(job,new Path("E:\\wordcount\\output"));

        boolean b = job.waitForCompletion(true);

        System.exit(b?0:1);
    }
}
