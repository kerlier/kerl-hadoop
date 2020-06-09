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
//        conf.set("mapreduce.framework.name","local");
//        conf.set("dfs.block.size","134217728");

        Job job = Job.getInstance(conf);

        job.setJarByClass(WordCountApplication.class);

        //设置class
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //设置map输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置最终的输出类型
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setNumReduceTasks(2);

        //设置输入path
        FileInputFormat.setInputPaths(job,new Path(args[0]));

        //设置输出path
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        boolean b = job.waitForCompletion(true);

        System.exit(b?0:1);
    }
}
