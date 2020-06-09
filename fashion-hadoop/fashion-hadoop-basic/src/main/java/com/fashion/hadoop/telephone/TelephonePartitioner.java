package com.fashion.hadoop.telephone;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;
import java.util.Map;

/**
 * hadoop中的partition 一直是从0开始的，默认会出现0这个分区
 */
public class TelephonePartitioner extends  Partitioner<Text,PhoneFlow> {

    Map<String,Integer> partitioers = new HashMap<String,Integer>(){
        {
            put("138",1);
            put("139",2);
        }
    };


    @Override
    public int getPartition(Text text, PhoneFlow phoneFlow, int numPartitions) {
        Integer partition = null;
        if(text.toString().startsWith("138")){
            partition =  partitioers.get("138");
        }else if(text.toString().startsWith("139")){
            partition =   partitioers.get("139");
        }
        return null== partition ? 3: partition;
    }
}
