package com.fashion.hadoop.order;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * writableComparator是作用在 reduce端的
 */
public class MyGroupComparator extends WritableComparator {

    /**
     * 继承writableComparator需要先createInstance
     */
    public MyGroupComparator(){
        super(OrderBean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean bean1 = (OrderBean)a;
        OrderBean bean2 = (OrderBean)b;
        return bean1.getOrderId().compareTo(bean2.getOrderId());
    }
}
