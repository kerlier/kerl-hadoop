package com.fashion.service;

import java.util.List;

/**
 * 推荐的结果我们一般会放到hbase或者redis等noSql数据库，查询速度比较快
 */
public interface RecommendService {

    /**
     * 基于用户的相似度进行推荐结果,批处理计算出来的
     * @param userId
     * @param needNum
     * @return
     */
    List<Object> recommendUser(String userId,int needNum);

    /**
     * 基于物品的离线推荐结果，前一天晚上计算出来的
     * @param userId
     * @param itemId
     * @param needNum
     * @return
     */
    List<Object> recommendItem(String userId,String itemId,int needNum);

    /**
     * 基于物品的相似度实时推荐结果，基于用户当天查看的物品的itemIds进行推荐
     *
     * 这里的物品相似度： 指的是如果多个物品被同时购买，可以将这多个物品看做相似物品
     * @param userId
     * @param views
     * @param needNum
     * @return
     */
    List<Object> recommendRealItem(String userId,String views,int needNum);

    /**
     * 基于物品内容的实时推荐结果
     * 物品内容指的是多个物品的属性可能一致
     * @param userId
     * @param view
     * @param needNum
     * @return
     */
    List<Object> recommendContentItem(String userId,String view,int needNum);
}
