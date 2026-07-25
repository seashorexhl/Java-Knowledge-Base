package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.April;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-07-14 13:53
 * @Description: 完善核心代码编程--分辨率排序--100分 ⭐⭐⭐
 *  多级自定义排序 + 面向对象的数据封装  + 字符串解析与数据清洗
 */
public class sortResolutions {
    static void main(String[] args) {
        String input= "3840x2160 3840x2161 3840x1080 2560x1440 1920x1080";
        sortResolutions sr = new sortResolutions();
        String sorted = sr.sortResolutions(input);
        System.out.println(sorted);
    }
    /**
     *  多级自定义排序 + 面向对象的数据封装  + 字符串解析与数据清洗
     * */
    public String sortResolutions(String input) {
        String[] resolutions = input.split(" ");
        List<Resolution> list = new ArrayList<>();
        for (String res : resolutions) {
            String[] parts = res.split("x");
            if (parts.length < 2) continue;
            int w = Integer.parseInt(parts[0]);
            int h = Integer.parseInt(parts[1]);
            int clarity = getClarity(w, h);
            int area = w * h;
            list.add(new Resolution(res, clarity, area, w));
        }

        Collections.sort(list, new Comparator<Resolution>() {
            @Override
            public int compare(Resolution a, Resolution b) {
                if (a.clarity != b.clarity) return b.clarity - a.clarity;
                else if (a.area != b.area) return b.area - a.area;
                else return b.width - a.width;
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).resStr);
            if (i < list.size() - 1) sb.append(" ");
        }
        return sb.toString();
    }

    private int getClarity(int w, int h) {
        if (w >= 3840 && h >= 2160) return 4;
        else if (w >= 2560 && h >= 1440) return 3;
        else if (w >= 1920 && h >= 1080) return 2;
        else return 1;
    }
    /**
     *  面向对象的数据封装
     * */
    static class Resolution {
        String resStr;
        int clarity;
        int area;
        int width;
        Resolution(String resStr, int clarity, int area, int width) {
            this.resStr = resStr;
            this.clarity = clarity;
            this.area = area;
            this.width = width;
        }
    }

}
