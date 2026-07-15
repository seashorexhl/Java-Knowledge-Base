package com.xhl.C04_Enterprise_Frameworks.NativeUtil.Util;

/**
 * @Author: xhl
 * @Date: 2026-07-15 10:18
 * @Description: 封装好的 JavaFileMerger 工具类
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Java 文件合并工具类
 * 用于递归获取目录下所有 .java 文件，并将文件名及内容写入指定的 txt 文件中
 */
public class JavaFileMerger {
    // 私有化构造方法，防止外部实例化
    private JavaFileMerger() {
        throw new UnsupportedOperationException("这是一个工具类，不允许被实例化！");
    }

    /**
     * 将指定目录下所有 .java 文件的内容合并写入到目标 txt 文件中 (按文件路径排序)
     *
     * @param sourceDir  源目录的绝对路径
     * @param targetFile 目标 txt 文件的绝对路径
     */
    public static void mergeJavaFiles(String sourceDir, String targetFile) {
        mergeJavaFiles(sourceDir, targetFile, true);
    }

    /**
     * 将指定目录下所有 .java 文件的内容合并写入到目标 txt 文件中
     *
     * @param sourceDir  源目录的绝对路径
     * @param targetFile 目标 txt 文件的绝对路径
     * @param sorted     是否按文件路径排序
     */
    public static void mergeJavaFiles(String sourceDir, String targetFile, boolean sorted) {
        Path sourcePath = Paths.get(sourceDir);
        Path targetPath = Paths.get(targetFile);

        try (Stream<Path> paths = Files.walk(sourcePath)) {
            // 1. 准备输出流
            try (BufferedWriter writer = Files.newBufferedWriter(targetPath)) {

                // 2. 构建文件流管道
                Stream<Path> javaFilesStream = paths
                        .filter(Files::isRegularFile)
                        .filter(path -> path.toString().endsWith(".java"));

                // 3. 根据参数决定是否排序
                if (sorted) {
                    javaFilesStream = javaFilesStream.sorted(Comparator.naturalOrder());
                }

                // 4. 遍历并写入内容
                javaFilesStream.forEach(javaFile -> {
                    try {
                        writeSingleFileToTxt(writer, javaFile);
                    } catch (IOException e) {
                        System.err.println("❌ 处理文件时发生IO异常: " + javaFile);
                        e.printStackTrace();
                    }
                });

                System.out.println("✅ 合并完成！目标文件: " + targetPath.toAbsolutePath());
            }

        } catch (IOException e) {
            System.err.println("❌ 遍历目录或创建目标文件失败: " + sourceDir);
            e.printStackTrace();
        }
    }

    /**
     * 将单个 Java 文件的内容写入到 BufferedWriter 中
     */
    private static void writeSingleFileToTxt(BufferedWriter writer, Path javaFile) throws IOException {
        // 写入文件头信息
        writer.write("========================================");
        writer.newLine();
        writer.write("📄 文件: " + javaFile.getFileName());
        writer.newLine();
        writer.write("📍 路径: " + javaFile.toAbsolutePath());
        writer.newLine();
        writer.write("========================================");
        writer.newLine();

        // 逐行读取并写入文件内容 (内存安全)
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

        // 文件内容结束后的分隔空行
        writer.newLine();
        writer.newLine();
    }
}
