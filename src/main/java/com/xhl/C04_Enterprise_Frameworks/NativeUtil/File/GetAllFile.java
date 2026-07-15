package com.xhl.C04_Enterprise_Frameworks.NativeUtil.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @Author: xhl
 * @Date: 2026-07-15 09:55
 * @Description:  使用 Java 原生 API 获取 子目录下的所有 .java 文件 （全文件名）
 */
public class GetAllFile {
    public static void main(String[] args) {
        String targetPath = "C:\\Users\\sanya\\Desktop\\Java-Knowledge-Base\\src\\main\\java\\com\\xhl\\C06_Algorithm_Skills\\ODExam\\A2026";

        // 使用 try-with-resources 确保流被正确关闭
        try (Stream<Path> paths = Files.walk(Paths.get(targetPath))) {
            paths
                    .filter(Files::isRegularFile)          // 1. 过滤掉文件夹，只保留文件
                    .filter(path -> path.toString().endsWith(".java")) // 2. 过滤出 .java 文件
                    .forEach(System.out::println);         // 3. 打印完整绝对路径
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
