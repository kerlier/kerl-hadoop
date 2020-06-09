package com.fashion.hadoop.telephone;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TelephoneReducer extends Reducer<Text, PhoneFlow, Text, PhoneFlow > {

    private PhoneFlow phoneFlow = new PhoneFlow();

    /**
     * reduce这时候读入的信息应该是
     * 聚合好的list
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<PhoneFlow> values, Context context) throws IOException, InterruptedException {


        Integer minFlowSum = 0;
        Integer maxFlowSum = 0;
        Integer sumFlow = 0;
        for(PhoneFlow phoneFlow:values){
            minFlowSum += phoneFlow.getMinFlow();
            maxFlowSum += phoneFlow.getMaxFlow();
            sumFlow += phoneFlow.getSumFlow();
        }

        phoneFlow.setMaxFlow(maxFlowSum);
        phoneFlow.setMinFlow(minFlowSum);
        phoneFlow.setSumFlow(sumFlow);

        context.write(key,phoneFlow);
    }
}
