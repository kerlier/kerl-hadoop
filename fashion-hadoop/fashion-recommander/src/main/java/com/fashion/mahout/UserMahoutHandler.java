package com.fashion.mahout;

import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 使用协同过滤算法
 */
public class UserMahoutHandler {

    public static void main(String[] args) throws Exception {

        //读取评分文件
        File file = new File("E:\\recommander\\ml_data\\ml-10m\\ml-10M100K\\ratings.dat");

        GroupLensDataModel dataModel = new GroupLensDataModel(file);

        UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);

        /**
         * userNeighborhood 实现有两种
         * NearestUserNeighborhood 对每个用户取最近的N个邻居
         * ThresholdUserNeighborhood 对用户进行一定的限制
         *
         */
        UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(100,similarity,dataModel);

        Recommender recommender  = new GenericUserBasedRecommender(dataModel,userNeighborhood,similarity);

        List<RecommendedItem> recommendedItemList = recommender.recommend(5, 10);

        recommendedItemList.forEach(System.out::println);
    }
}
