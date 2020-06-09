package com.fashion.hadoop.order;

import com.fashion.hadoop.telephone.PhoneFlow;
import com.fashion.hadoop.telephone.TelephoneMapper;
import com.fashion.hadoop.telephone.TelephonePartitioner;
import com.fashion.hadoop.telephone.TelephoneReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaxOrderApplication {

    /**
     * 写驱动类
     * @param args
     */
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
//        conf.set("mapreduce.framework.name","local");

        //jar包运行的时候不会报错
        job.setJarByClass(MaxOrderApplication.class);

        job.setMapperClass(OrderMapper.class);
        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setGroupingComparatorClass(MyGroupComparator.class);
        job.setPartitionerClass(OrderPartitioner.class);

        job.setReducerClass(OrderReducer.class);

        //设置最终输出的结果类型
        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(2);

        FileInputFormat.setInputPaths(job, new Path("E:\\git\\kerl-hadoop\\fashion-hadoop\\a.txt"));

        FileOutputFormat.setOutputPath(job,new Path("E:\\output"));

        boolean b = job.waitForCompletion(true);

        // 0表示正常退出， 1表示非正常退出
        System.exit(b?0:1);
    }
}
