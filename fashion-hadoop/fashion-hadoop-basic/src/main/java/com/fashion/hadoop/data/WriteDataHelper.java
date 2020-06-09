package com.fashion.hadoop.data;

import java.io.*;
import java.util.Random;

public class WriteDataHelper {

    public static void main(String[] args) throws IOException {

        File file = new File("F:\\big-data\\hadoop\\input\\word.txt");
        FileOutputStream fos = new FileOutputStream(file,true);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < 10000000; i++) {

            StringBuffer stringBuffer = new StringBuffer();
            for (int j = 0; j < 10; j++) {
                String name = getStringRandom(10);
                stringBuffer.append(name).append(" ");
            }
            writer.write(stringBuffer.toString() + "\r\n");
        }

        writer.close();
        fos.close();
    }

    //生成随机用户名，数字和字母组成,
    public static String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
