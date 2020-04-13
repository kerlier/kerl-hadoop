package com.fashion.hadoop.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text, IntWritable,Text,IntWritable> {

    /**
     * reduce的输入格式是 (hadoop,<1,1,1>)
     *
     * reduce接收所有来自map阶段处理的数据之后，按照key的字典序进行排序
     * 按照key是否相同作为一组去调用reduce方法
     *      * 本方法的key就是这一组相同kv对的共同key
     *      * 把这一组所有的v作为一个迭代器传入我们的reduce方法
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for (IntWritable value: values) {
            count+= value.get();
        }
        context.write(key,new IntWritable(count));
    }
}
