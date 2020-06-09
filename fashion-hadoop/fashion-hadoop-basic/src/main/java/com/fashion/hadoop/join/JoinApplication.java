package com.fashion.hadoop.join;

import com.fashion.hadoop.multiout.MultiReducer;
import com.fashion.hadoop.multiout.TelephoneApplication;
import com.fashion.hadoop.multiout.TelephoneMapper;
import com.fashion.hadoop.telephone.PhoneFlow;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class JoinApplication {


    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("mapreduce.framework.name","local");

        Job job = Job.getInstance(conf);

        //jar包运行的时候不会报错
        job.setJarByClass(JoinApplication.class);

        job.setMapperClass(JoinMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(InfoBean.class);

        job.setReducerClass(JoinReducer.class);

        //设置最终输出的结果类型
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(InfoBean.class);
        job.setNumReduceTasks(1);

        FileInputFormat.setInputPaths(job, new Path(args[0]));

        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        boolean b = job.waitForCompletion(true);

        // 0表示正常退出， 1表示非正常退出
        System.exit(b?0:1);
    }
}
