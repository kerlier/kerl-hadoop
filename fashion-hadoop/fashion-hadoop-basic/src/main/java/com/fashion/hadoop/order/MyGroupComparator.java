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

    /**
     * writableComparator 是将两个值相同的放到一个组内，默认是取第一个记录作为这个组的key
     * @param a
     * @param b
     * @return
     */
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean bean1 = (OrderBean)a;
        OrderBean bean2 = (OrderBean)b;
        return bean1.getOrderId().compareTo(bean2.getOrderId());
    }
}
