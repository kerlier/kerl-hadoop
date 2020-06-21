package com.fashion.mahout;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;

import java.io.File;

/**
 * 对模型进行准确率评估
 */
public class MahoutEvaluate {

    public static void main(String[] args)throws  Exception {

        File file = new File("E:\\recommander\\ml_data\\ml-10m\\ml-10M100K\\ratings.dat");

        GroupLensDataModel  dataModel = new GroupLensDataModel(file);

        //使用均方根进行评估
        RecommenderEvaluator  evaluator= new RMSRecommenderEvaluator();

        RecommenderEvaluator  evaluator1 = new AverageAbsoluteDifferenceRecommenderEvaluator();


        RecommenderBuilder recommenderBuilder =  new RecommenderBuilder(){

            @Override
            public Recommender buildRecommender(DataModel dataModel) throws TasteException {
                UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
                UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(10, userSimilarity,dataModel);
                return new GenericUserBasedRecommender(dataModel,userNeighborhood,userSimilarity);
            }
        };

        // 用70%做训练，剩下的30%做测试
        double score = evaluator1.evaluate(recommenderBuilder,null, dataModel,0.7,1.0);

        //最后得到的评估值越小，推荐结果越好
        System.out.println(score);
    }
}
