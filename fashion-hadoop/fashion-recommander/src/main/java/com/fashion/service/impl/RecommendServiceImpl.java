package com.fashion.service.impl;

import com.fashion.service.RecommendService;

import java.util.List;

/**
 * 实现从noSql数据库取数据的逻辑
 */
public class RecommendServiceImpl implements RecommendService {
    /**
     * 基于用户的相似度进行推荐结果,批处理计算出来的
     *
     * @param userId
     * @param needNum
     * @return
     */
    @Override
    public List<Object> recommendUser(String userId, int needNum) {
        return null;
    }

    /**
     * 基于物品的离线推荐结果，前一天晚上计算出来的
     *
     * @param userId
     * @param itemId
     * @param needNum
     * @return
     */
    @Override
    public List<Object> recommendItem(String userId, String itemId, int needNum) {
        return null;
    }

    /**
     * 基于物品的相似度实时推荐结果，基于用户当天查看的物品的itemIds进行推荐
     * <p>
     * 这里的物品相似度： 指的是如果多个物品被同时购买，可以将这多个物品看做相似物品
     *
     * @param userId
     * @param views
     * @param needNum
     * @return
     */
    @Override
    public List<Object> recommendRealItem(String userId, String views, int needNum) {
        return null;
    }

    /**
     * 基于物品内容的实时推荐结果
     * 物品内容指的是多个物品的属性可能一致
     *
     * @param userId
     * @param view
     * @param needNum
     * @return
     */
    @Override
    public List<Object> recommendContentItem(String userId, String view, int needNum) {
        return null;
    }
}
