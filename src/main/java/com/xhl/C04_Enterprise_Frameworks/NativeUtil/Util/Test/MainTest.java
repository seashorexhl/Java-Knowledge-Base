package com.xhl.C04_Enterprise_Frameworks.NativeUtil.Util.Test;

import com.xhl.C04_Enterprise_Frameworks.NativeUtil.Util.JavaFileMerger;

/**
 * @Author: xhl
 * @Date: 2026-07-15 10:21
 * @Description:    主程序中使用 工具类 ⭐⭐⭐ \C06_Algorithm_Skills\ODExam\A2026
 */
public class MainTest {
    public static void main(String[] args) {
        String sourceDir = "C:\\Users\\sanya\\Desktop\\Java-Knowledge-Base\\src\\main\\java\\com\\xhl";
        String targetFile = "C:\\Users\\sanya\\Desktop\\Contents.txt";

        // 调用工具类 (默认按路径排序)
        JavaFileMerger.mergeJavaFiles(sourceDir, targetFile);

        // 如果不需要排序，可以传 false，速度会更快
        // JavaFileMerger.mergeJavaFiles(sourceDir, targetFile, false);
    }
}
