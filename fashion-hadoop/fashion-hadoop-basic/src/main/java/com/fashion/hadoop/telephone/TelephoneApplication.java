package com.fashion.hadoop.telephone;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class TelephoneApplication {

    /**
     * 写驱动类
     * @param args
     */
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        //jar包运行的时候不会报错
        job.setJarByClass(TelephoneApplication.class);

        job.setMapperClass(TelephoneMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(PhoneFlow.class);

        job.setPartitionerClass(TelephonePartitioner.class);

        job.setReducerClass(TelephoneReducer.class);

        //设置最终输出的结果类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(PhoneFlow.class);

        job.setNumReduceTasks(4);

        FileInputFormat.setInputPaths(job, new Path(args[0]));

        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        boolean b = job.waitForCompletion(true);

        // 0表示正常退出， 1表示非正常退出
        System.exit(b?0:1);
    }
}
