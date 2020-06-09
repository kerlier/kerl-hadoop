package com.fashion.hadoop.order;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {

    OrderBean orderBean = new OrderBean();
    Text orderKey = new Text();
    DoubleWritable orderAmount = new DoubleWritable();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();

        String[] strings = line.split(" ");

        if(strings.length == 3){
            orderKey.set(strings[0]);
            Double amount = Double.parseDouble(strings[2]);
            orderAmount.set(amount);

            orderBean.setOrderId(orderKey);
            orderBean.setAmount(orderAmount);
            context.write(orderBean, NullWritable.get());
        }
    }
}
