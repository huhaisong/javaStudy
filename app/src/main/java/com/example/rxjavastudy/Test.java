package com.example.rxjavastudy;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Test {

    private static final String TAG = "Test";

    public static void main(String[] args) {
        String string = "[UserInfo{0:Owner:13}, UserInfo{10:more:30}, UserInfo{11:more:30}, UserInfo{12:more:30}]";
        String temp = string.replaceAll("UserInfo", "");
        temp = temp.replaceAll("\\[", "");
        temp = temp.replaceAll("\\]", "");
        temp = temp.replaceAll("\\{", "");
        temp = temp.replaceAll("\\}", "");
        temp = temp.replaceAll(" ", "");
        String[] strings = temp.split(",");
        ArrayList<Integer> integers = new ArrayList<>();
        if (strings.length > 0) {
            for (int i = 0; i < strings.length; i++) {
                String[] strings1 = strings[i].split(":");
                try {
                    int firstNumb = Integer.valueOf(strings1[0]);
                    integers.add(firstNumb);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (strings1.length == 3) {
                    System.out.println("第" + i + "个UserInfo 的两个数字为：第一个为：" + strings1[0] + "，第二个为：" + strings1[2]);
                }
            }
        }
        System.out.println("第一个数字的ArrayList = " + integers.toString());
    }
}
