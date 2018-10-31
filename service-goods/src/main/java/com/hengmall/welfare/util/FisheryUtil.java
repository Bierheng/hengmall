package com.hengmall.welfare.util;


import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2018/9/29 0029.
 */
public class FisheryUtil {

    public static <T> Map<List<Integer>, T> getFisheryMap(List<T> list, String probabilityAttr){

        Map<List<Integer>, T> map = new HashMap<>();
        int record = 0;
        for (T t : list) {
            Integer probability = (Integer) getFisheryProbability(t, probabilityAttr);
            map.put(getProbabilityList(record, record + probability), t);
            record += probability;
        }
        return map;
    }



    public static <T> Object getFisheryProbability(T t,String probabilityAttr) {
        Class<?> aClass = t.getClass();
        try {
            probabilityAttr = "get"+probabilityAttr.substring(0,1).toUpperCase()+probabilityAttr.substring(1);
            Method setReadOnly = t.getClass().getMethod(probabilityAttr);
            Object invoke = setReadOnly.invoke(t);
            return invoke;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> T getActivityProperty(Map<List<Integer>, T> jackpotMap,Integer weight ) {
            int randomNumber = new Random().nextInt(weight);
            Iterator it = jackpotMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, T> entry = (Map.Entry<String, T>) it.next();

                if (entry.getKey().contains(randomNumber+"")) {
                    return  entry.getValue();
                }
            }
        return null;
   }

    public static List<Integer> getProbabilityList(Integer start, Integer finish) {
        List<Integer> retList = new LinkedList<>();
        for (int i = start; i < finish; i++) {
            retList.add(i);
        }
        return retList;
    }

    public static <T> T getRandomMapValue(Map<String, T> jackpotMap,Integer weight) {
        int randomNumber = new Random().nextInt(weight);
        Iterator it = jackpotMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, T> entry = (Map.Entry<String, T>) it.next();

            if (entry.getKey().contains(randomNumber+",")) {
                return  entry.getValue();
            }
        }
        return null;
    }
}
