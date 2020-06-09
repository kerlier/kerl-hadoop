package com.fashion.hadoop.join;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;

public class InfoBean implements Writable {

    private String orderId;
    private String date;
    private String pId;
    private String pName;
    private String categoryId;
    private Integer price;
    private String flag;

    public InfoBean(){
        this.orderId ="";
        this.date ="";
        this.pId = "";
        this.pName ="";
        this.categoryId = "";
        this.price = 0;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return orderId+"\t"+ date+"\t"+ pId+"\t"+ pName+"\t"+ categoryId+"\t"+price;
    }


    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(orderId);
        out.writeUTF(date);
        out.writeUTF(pId);
        out.writeUTF(pName);
        out.writeUTF(categoryId);
        out.writeInt(price);
        out.writeUTF(flag);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        orderId= in.readUTF();
        date = in.readUTF();
        pId = in.readUTF();
        pName = in.readUTF();
        categoryId = in.readUTF();
        price = in.readInt();
        flag = in.readUTF();
    }
}
