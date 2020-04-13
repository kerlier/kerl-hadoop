package com.fashion.hadoop.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Mapper
 * 第一个参数表示偏移量
 * 第二个参数表示输入的类型
 * 第三个参数表示输出key的类型
 * 第三个参数表示输出value的类型
 *
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    /**
     * map 方法是mapper实际的处理逻辑
     * @param key  输入的偏移量
     * @param value ： 输入的value值
     * @param context 表示上下文
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //toString 转换成 string类型
        String line = value.toString();
        String[] words = line.split(" ");

        for (String word:words) {
            context.write(new Text(word),new IntWritable(1));
        }
    }
}
