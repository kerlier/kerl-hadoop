package com.fashion.hadoop.multiout;

import com.fashion.hadoop.telephone.PhoneFlow;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 首先确定: 输入的日志格式
 *
 * 13897230503 400 1300
 * 13897230503 100 300
 *
 * 分析: mapper读取一行数据后， 13897230503 400 1300
 * 需要根据空格切分
 * key 应该是手机号
 * value应该是 手机号的流量 400， 1300
 *
 * 输出的格式
 * 13897230503 500 1600 2100
 */
public class TelephoneMapper extends Mapper<LongWritable,Text, Text, PhoneFlow> {

    private PhoneFlow phoneFlow = new PhoneFlow();
    private Text mapKey = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();

        //根据空格切分
        String[] splits = line.split(" ");

        if(splits.length == 3){
            String phoneNumber = splits[0];
            Integer minFlow = Integer.parseInt(splits[1]);
            Integer maxFlow = Integer.parseInt(splits[2]);

            phoneFlow.setMinFlow(minFlow);
            phoneFlow.setMaxFlow(maxFlow);
            phoneFlow.setSumFlow(maxFlow+minFlow);

            mapKey.set(phoneNumber);
            context.write(mapKey,phoneFlow);
        }
    }
}
