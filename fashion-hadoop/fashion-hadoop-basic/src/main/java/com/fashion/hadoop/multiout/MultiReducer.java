package com.fashion.hadoop.multiout;

import com.fashion.hadoop.telephone.PhoneFlow;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

public class MultiReducer extends Reducer<Text, PhoneFlow, Text,PhoneFlow> {

    private  MultipleOutputs<NullWritable, PhoneFlow> multipleOutputs;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        multipleOutputs = new MultipleOutputs(context);
    }


    /**
     * mulpleOutputs中有参数完全不同的方法
     * 第一个write方法， 第三个参数指的是输出路径，第二个参数表示value, 第三个参数表示的是key
     * 第一个方法可以实现以变化的key作为文件的输出， 第二种方法不能实现这种方法， 它只能提前声明好namedOutput
     * write(KEYOUT key, VALUEOUT value, String baseOutputPath)
     *
     * 第一个参数表示的是输出文件的名字，第二个表示的key,第三个表示的value
     * write(String namedOutput, K key, V value)
     *
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<PhoneFlow> values, Context context) throws IOException, InterruptedException {
        for (PhoneFlow phoneFlow:values) {
            multipleOutputs.write(NullWritable.get(),phoneFlow,key.toString());
        }
    }

    /**
     * Called once at the end of the task.
     *
     * @param context
     */
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        multipleOutputs.close();
    }
}
