package com.xhl.C04_Enterprise_Frameworks.NativeUtil.File;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @Author: xhl
 * @Date: 2026-07-15 09:51
 * @Description: 遍历目录下的 所有文件 (不包括子目录)
 */
public class CommandFileLister {
    public static void main(String[] args) {
        // 注意：在 Java 字符串中，Windows 路径的反斜杠 \ 必须转义为 \\
        String targetPath = "C:\\Users\\sanya\\Desktop\\Java-Knowledge-Base\\src\\main\\java\\com\\xhl\\C07_Algorithm_Skills\\ODExam\\A2026";

        listFilesByCommand(targetPath);
    }

    public static void listFilesByCommand(String dirPath) {
        try {
            // Windows 下使用 cmd /c dir /b 获取纯净的文件名列表
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "dir", "/b", dirPath);

            // 合并标准输出和错误输出（如果目录不存在，错误信息也会通过流返回）
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // 【关键】Windows 命令行默认输出编码为 GBK，必须指定，否则中文文件名会乱码
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), Charset.forName("GBK"))
            );

            String line;
            System.out.println("=== 目录 [" + dirPath + "] 下的文件列表 ===");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("命令执行异常，退出码: " + exitCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
