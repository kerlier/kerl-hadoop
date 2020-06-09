package com.fashion.hadoop.order;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {

    private Text orderId;

    private DoubleWritable amount;

    public Text getOrderId() {
        return orderId;
    }

    public void setOrderId(Text orderId) {
        this.orderId = orderId;
    }

    public DoubleWritable getAmount() {
        return amount;
    }

    public void setAmount(DoubleWritable amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(OrderBean o) {
        if(this.orderId.compareTo(o.orderId)==0){
            if(this.amount.get() > o.getAmount().get()){
                return -1;
            }
        }
        return 0;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.orderId.toString());
        out.writeDouble(this.amount.get());
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.orderId = new Text(in.readUTF());
        this.amount =  new DoubleWritable(in.readDouble());
    }

    @Override
    public String toString() {
        return this.orderId.toString() + "\t" + this.amount.get();
    }
}
