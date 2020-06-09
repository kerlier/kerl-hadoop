package com.fashion.hadoop.join;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class JoinMapper extends Mapper<LongWritable, Text,Text,InfoBean> {

    InfoBean infoBean =   new InfoBean();

    Text text = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] strs = line.split(" ");

        //获取块的名字
        FileSplit fileSplit =(FileSplit) context.getInputSplit();

        String name = fileSplit.getPath().getName();

        if(name.startsWith("order")){
            infoBean.setOrderId(strs[0]);
            infoBean.setDate(strs[1]);
            infoBean.setpId(strs[2]);
            infoBean.setFlag("1");
        }else if(name.startsWith("product")){
            infoBean.setpId(strs[0]);
            infoBean.setpName(strs[1]);
            infoBean.setCategoryId(strs[2]);
            infoBean.setPrice(Integer.valueOf(strs[3]));
            infoBean.setFlag("2");
        }
        text.set(infoBean.getpId());
        context.write(text, infoBean);
    }
}
