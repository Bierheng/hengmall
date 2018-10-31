package com.hengmall.user.util;

import java.util.Random;

public class RandomUtil {

    /**
     * 取指定随机次数8~12的随机数和
     * @param count 随机次数
     * @return
     */
    public static int RandomMult8To12SumValue(int count){
        Random rand = new Random();
        int sum=0;
        if(count>0){
            for(int i=0; i<count; i++) {
                sum+=rand.nextInt(5) + 8;
            }
        }
        return sum;
    }

    /**
     * 取指定随机次数3~5的随机数和
     * @param count 随机次数
     * @return
     */
    public static int RandomMult3To5SumValue(int count){
        Random rand = new Random();
        int sum=0;
        if(count>0){
            for(int i=0; i<count; i++) {
                sum+=rand.nextInt(3) + 3;
            }
        }
        return sum;
    }


    /**
     * 取指定随机次数10000~11000的随机身价
     * @return
     */
    public static int RandomSellingPrice(){
        Random rand = new Random();
        int sum=rand.nextInt(1001) + 10000;
        return sum;
    }

    /**
     * 取某个范围的随机数
     * @param min
     * @param max
     * @return
     */
    public static int random(int min,int max){
        Random rand = new Random();
        int sum=rand.nextInt(max-min+1) + min;
        return sum;
    }

}
