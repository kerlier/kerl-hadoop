package com.fashion.hadoop.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.UUID;

public class WriteDataHelper2 {

    public static void main(String[] args) {

        //写100个人的信息
        try {
            writeLog();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void writePerson() throws  Exception{
        File file = new File("F:\\big-data\\hadoop\\input\\person.txt");
        FileOutputStream fos = new FileOutputStream(file,true);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        Random random = new Random();
        for (int i = 0; i < 100 ; i++) {
            StringBuffer line = new StringBuffer();
            line.append(i).append("\t").append(random.nextInt(2)).append("\t").append(UUID.randomUUID().toString().substring(0,10)).append("\t\n");
            writer.write(line.toString());
        }
        writer.close();
        fos.close();
    }

    public static void writeLog() throws  Exception{
        File file = new File("F:\\big-data\\hadoop\\input\\log.txt");
        FileOutputStream fos = new FileOutputStream(file,true);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        Random random = new Random();
        for (int i = 0; i < 100 ; i++) {
            if(i == 99){
                //写10亿行log
                for(int j = 0; j< 30000000; j++){
                    StringBuffer line = new StringBuffer();
                    line.append(i).append("\t").append(UUID.randomUUID().toString()).append("\r\n");
                    writer.write(line.toString());
                }
            }else{
                int i1 = random.nextInt(100);
                for(int j = 0; j< i1; j++){
                    StringBuffer line = new StringBuffer();
                    line.append(i).append("\t").append(UUID.randomUUID().toString()).append("\r\n");
                    writer.write(line.toString());
                }
            }
        }
        writer.close();
        fos.close();
    }

}
