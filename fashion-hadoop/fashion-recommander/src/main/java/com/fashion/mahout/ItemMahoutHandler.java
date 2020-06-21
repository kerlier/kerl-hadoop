package com.fashion.mahout;

import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;

import java.io.File;
import java.util.List;

/**
 * 基于物品的推荐
 */
public class ItemMahoutHandler {

    public static void main(String[] args)throws  Exception {

        File file = new File("E:\\recommander\\ml_data\\ml-10m\\ml-10M100K\\ratings.dat");

        DataModel  dataModel = new GroupLensDataModel(file);
        ItemSimilarity  itemSimilarity = new PearsonCorrelationSimilarity(dataModel);

        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dataModel,itemSimilarity);


        //给用户5推荐跟物品2398相似的10件商品
        List<RecommendedItem> recommendedItems = recommender.recommendedBecause(5, 2398, 10);

        recommendedItems.forEach(System.out::println);
    }
}
