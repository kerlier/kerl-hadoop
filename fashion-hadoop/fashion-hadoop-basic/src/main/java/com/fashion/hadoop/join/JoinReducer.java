package com.fashion.hadoop.join;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 这里已经将同一个pid的infoBean都放到了一个reduce中
 */
public class JoinReducer extends Reducer<Text,InfoBean, NullWritable, InfoBean> {

    @Override
    protected void reduce(Text key, Iterable<InfoBean> values, Context context) throws IOException, InterruptedException {

        //将当前的Product的信息先存起来
        InfoBean productInfo = new InfoBean();
        List<InfoBean> infoBeans = new ArrayList<>();
        try {
            for (InfoBean infoBean : values) {
                // 1 表示是order
                if(infoBean.getFlag().equals("1")){
                    InfoBean infoBean1 = new InfoBean();
                    BeanUtils.copyProperties(infoBean1,infoBean);
                    infoBeans.add(infoBean1);
                }else if(infoBean.getFlag().equals("2")){//2表示是product
                    BeanUtils.copyProperties(productInfo,infoBean);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        for (InfoBean infoBean:infoBeans) {
            infoBean.setpName(productInfo.getpName());
            infoBean.setCategoryId(productInfo.getCategoryId());
            infoBean.setPrice(productInfo.getPrice());
            context.write(NullWritable.get(),infoBean);
        }
    }
}
