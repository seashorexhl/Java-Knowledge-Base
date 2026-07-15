package com.xhl.C04_Enterprise_Frameworks.NativeUtil.File;

import java.io.File;

/**
 * @Author: xhl
 * @Date: 2026-07-15 09:53
 * @Description: 获取该目录下所有的 .java 文件 （不包括子目录）
 */
public class NativeFileLister {
    public static void main(String[] args) {
        String targetPath = "C:\\Users\\sanya\\Desktop\\Java-Knowledge-Base\\src\\main\\java\\com\\xhl\\C06_Algorithm_Skills\\ODExam\\A2026";
        File dir = new File(targetPath);

        // 获取该目录下所有的 .java 文件
        File[] javaFiles = dir.listFiles((d, name) -> name.endsWith(".java"));

        if (javaFiles != null) {
            for (File file : javaFiles) {
                System.out.println(file.getName());
            }
        }
    }
}
