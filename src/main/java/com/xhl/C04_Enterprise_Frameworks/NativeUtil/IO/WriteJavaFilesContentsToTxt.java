package com.xhl.C04_Enterprise_Frameworks.NativeUtil.IO;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @Author: xhl
 * @Date: 2026-07-15 10:12
 * @Description:  写入文件 目录名 和 内容 到 txt
 *
 */
public class WriteJavaFilesContentsToTxt {
    public static void main(String[] args) {
        String targetPath = "C:\\Users\\sanya\\Desktop\\Java-Knowledge-Base\\src\\main\\java\\com\\xhl\\C06_Algorithm_Skills\\ODExam\\A2026";
        String outputTxt = "C:\\Users\\sanya\\Desktop\\ODQuestionAnwser.txt";

        try (Stream<Path> paths = Files.walk(Paths.get(targetPath))) {

            // 使用 try-with-resources 自动管理输出流
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputTxt))) {

                paths
                        .filter(Files::isRegularFile)
                        .filter(path -> path.toString().endsWith(".java"))
                        .sorted() // 按路径排序，保证写入顺序一致
                        .forEach(javaFile -> {
                            try {
                                // 1. 写入文件头信息
                                writer.write("========================================");
                                writer.newLine();
                                writer.write("📄 文件: " + javaFile.getFileName());
                                writer.newLine();
                                writer.write("📍 路径: " + javaFile.toAbsolutePath());
                                writer.newLine();
                                writer.write("========================================");
                                writer.newLine();

                                // 2. 读取并写入文件内容
                                // Files.lines() 会逐行读取文件内容，不会一次性加载到内存
                                try (Stream<String> lines = Files.lines(javaFile)) {
                                    lines.forEach(line -> {
                                        try {
                                            writer.write(line);
                                            writer.newLine();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    });
                                }

                                // 3. 文件内容结束后加一个空行，分隔下一个文件
                                writer.newLine();
                                writer.newLine();

                            } catch (IOException e) {
                                System.err.println("❌ 读取文件失败: " + javaFile);
                                e.printStackTrace();
                            }
                        });

                System.out.println("✅ 所有 Java 文件及内容已成功写入: " + outputTxt);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
