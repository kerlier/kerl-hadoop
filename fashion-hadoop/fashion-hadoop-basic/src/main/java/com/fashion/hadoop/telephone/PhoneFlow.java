package com.fashion.hadoop.telephone;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 *
 * 自定义bean的时候，需要继承hadoop中的writable
 *
 * 自定义的bean，需要重写toString 方法， 这样在写到文件中保证是一个string字符串
 */
public class PhoneFlow implements Writable {

    private Integer minFlow;

    private Integer maxFlow;

    private Integer sumFlow;

    public Integer getMinFlow() {
        return minFlow;
    }

    public void setMinFlow(Integer minFlow) {
        this.minFlow = minFlow;
    }

    public Integer getMaxFlow() {
        return maxFlow;
    }

    public void setMaxFlow(Integer maxFlow) {
        this.maxFlow = maxFlow;
    }

    public Integer getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(Integer sumFlow) {
        this.sumFlow = sumFlow;
    }

    /**
     * 需要按照顺序来读
     * @param out
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(this.minFlow);
        out.writeInt(this.maxFlow);
        out.writeInt(this.sumFlow);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
       this.minFlow =  in.readInt();
       this.maxFlow = in.readInt();
       this.sumFlow = in.readInt();
    }

    @Override
    public String toString() {
        return this.minFlow +"\t" + this.maxFlow+"\t" + this.sumFlow;
    }
}
