package com.fashion.mahout;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;

import java.io.File;

/*
    获取准确率以及召回率

    准确率： 真正与user相关的推荐物品个数/ 预测与user相关的物品个数
    召回率： 真正与user相关的推荐物品个数/ user相关的物品个数
 */
public class PrecisionAndRecall {
    public static void main(String[] args) throws  Exception {

        File file = new File("E:\\recommander\\ml_data\\ml-10m\\ml-10M100K\\ratings.dat");

        DataModel  dataModel = new GroupLensDataModel(file);

        RecommenderIRStatsEvaluator statsEvaluator =  new GenericRecommenderIRStatsEvaluator();

        RecommenderBuilder recommenderBuilder =  new RecommenderBuilder(){

            @Override
            public Recommender buildRecommender(DataModel dataModel) throws TasteException {
                UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
                //找最近的4个邻居
                UserNeighborhood  neighborhood  = new NearestNUserNeighborhood(4, userSimilarity,dataModel);
                return new GenericUserBasedRecommender(dataModel,neighborhood,userSimilarity);
            }
        };

        IRStatistics evaluate = statsEvaluator.evaluate(recommenderBuilder, null, dataModel, null, 4, GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, 1.0);

        //准确率
        System.out.println(evaluate.getPrecision());
        //召回率
        System.out.println(evaluate.getRecall());
    }
}
