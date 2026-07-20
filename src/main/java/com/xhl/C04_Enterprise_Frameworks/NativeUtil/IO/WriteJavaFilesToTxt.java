package com.xhl.C04_Enterprise_Frameworks.NativeUtil.IO;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @Author: xhl
 * @Date: 2026-07-15 10:09
 * @Description: 写入文件 目录名 到 txt
 *  纯目录
 */
public class WriteJavaFilesToTxt {
    public static void main(String[] args) {
        String targetPath = "C:\\Users\\sanya\\Desktop\\Java-Knowledge-Base\\src\\main\\java\\com\\xhl\\C07_Algorithm_Skills\\ODExam\\A2026";
        String outputTxt = "C:\\Users\\sanya\\Desktop\\ODQuestion.txt"; // 输出文件路径

        // 1. 获取源目录文件流
        try (Stream<Path> paths = Files.walk(Paths.get(targetPath))) {

            // 2. 创建输出文件的写入流 (try-with-resources 会自动关闭流)
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputTxt))) {

                paths
                        .filter(Files::isRegularFile)                  // 过滤掉文件夹
                        .filter(path -> path.toString().endsWith(".java")) // 过滤 .java 文件
                        .map(Path::getFileName)                        // 提取纯文件名
                        .sorted()                                      // 按字母排序（可选）
                        .forEach(fileName -> {
                            try {
                                writer.write(fileName.toString());     // 写入文件名
                                writer.newLine();                      // 换行
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                System.out.println("✅ 文件列表已成功写入: " + outputTxt);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
